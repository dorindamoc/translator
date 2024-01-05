package com.example.translator_kmm.translate.presentation

import com.example.translator_kmm.core.domain.util.Resource
import com.example.translator_kmm.core.domain.util.toCommonStateFlow
import com.example.translator_kmm.core.presentation.TranslateEvent
import com.example.translator_kmm.core.presentation.TranslateState
import com.example.translator_kmm.core.presentation.UiLanguage
import com.example.translator_kmm.translate.domain.history.HistoryDataSource
import com.example.translator_kmm.translate.domain.translate.Translate
import com.example.translator_kmm.translate.domain.translate.TranslateException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class TranslateViewModel(
    private val translate: Translate,
    private val historyDataSource: HistoryDataSource,
    private val coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: MainScope()

    private val _state = MutableStateFlow(TranslateState())

    // Combines the ui state and the data source state into one state of our type of flow
    val state = combine(
        _state, historyDataSource.getHistory()
    ) { state, history ->
        if (state.history != history) {
            state.copy(history = history.mapNotNull {
                UiHistoryItem(
                    id = it.id ?: return@mapNotNull null,
                    fromLanguage = UiLanguage.byCode(it.fromLanguageCode),
                    fromText = it.fromText,
                    toLanguage = UiLanguage.byCode(it.toLanguageCode),
                    toText = it.toText
                )
            })
        } else state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TranslateState())
        .toCommonStateFlow()

    private var translateJob: Job? = null

    fun onEvent(event: TranslateEvent) {
        when (event) {
            is TranslateEvent.ChangeTranslationText -> {
                _state.update {
                    it.copy(fromText = event.text)
                }
            }

            is TranslateEvent.ChooseFromLanguage -> {
                _state.update {
                    it.copy(
                        isChoosingFromLanguage = false,
                        fromLanguage = event.language
                    )
                }
            }

            is TranslateEvent.ChooseToLanguage -> {
                val newState = _state.updateAndGet {
                    it.copy(
                        isChoosingToLanguage = false,
                        toLanguage = event.language
                    )
                }
                translate(newState)
            }

            TranslateEvent.CloseTranslation -> {
                _state.update {
                    it.copy(
                        isTranslating = false,
                        fromText = "",
                        toText = null,
                    )
                }
            }

            TranslateEvent.EditTranslation -> {
                if (_state.value.toText != null) {
                    _state.update {
                        it.copy(
                            isTranslating = false,
                            toText = null,
                        )
                    }
                }
            }

            TranslateEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }

            TranslateEvent.OpenFromLanguageDropdown -> {
                _state.update {
                    it.copy(
                        isChoosingFromLanguage = true
                    )
                }
            }

            TranslateEvent.OpenToLanguageDropdown -> {
                _state.update {
                    it.copy(
                        isChoosingToLanguage = true
                    )
                }
            }

            is TranslateEvent.SelectHistoryItem -> {
                translateJob?.cancel()
                _state.update {
                    it.copy(
                        fromText = event.item.fromText,
                        toText = event.item.toText,
                        fromLanguage = event.item.fromLanguage,
                        toLanguage = event.item.toLanguage,
                        isTranslating = false,
                    )
                }
            }

            TranslateEvent.StopChoosingLanguage -> {
                _state.update {
                    it.copy(
                        isChoosingFromLanguage = false,
                        isChoosingToLanguage = false,
                    )
                }
            }

            is TranslateEvent.SubmitVoiceResult -> {
                _state.update {
                    it.copy(
                        fromText = event.result ?: it.fromText,
                        isTranslating = if(event.result != null) false else it.isTranslating,
                        toText = if(event.result != null) null else it.toText,

                    )
                }
            }

            TranslateEvent.SwapLanguages -> {
                _state.update {
                    it.copy(
                        fromLanguage = it.toLanguage,
                        toLanguage = it.fromLanguage,
                        fromText = it.toText ?: "",
                        toText = if (it.toText != null) null else it.toText,
                    )
                }
            }
            TranslateEvent.Translate -> translate(state.value)
            else -> Unit
        }
    }


    fun translate(state: TranslateState) {
        if (state.isTranslating || state.fromText.isBlank()) {
            return
        }
        translateJob = viewModelScope.launch {
            // _state.value = _state.value.copy() creates potential race conditions
            _state.update {
                it.copy(isTranslating = true)
            }
            val result = translate.execute(
                fromLanguage = state.fromLanguage.language,
                toLanguage = state.toLanguage.language,
                fromText = state.fromText
            )
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isTranslating = false, toText = result.data
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isTranslating = false,
                            error = (result.throwable as? TranslateException)?.error,
                        )
                    }
                }
            }
        }
    }
}