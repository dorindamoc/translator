package com.example.translator_kmm.core.presentation

import com.example.translator_kmm.translate.presentation.UiHistoryItem


// Sealed instead of interface because it translates more convenient to ios code
sealed class TranslateEvent {
    data class ChooseFromLanguage(val language: UiLanguage) : TranslateEvent()
    data class ChooseToLanguage(val language: UiLanguage) : TranslateEvent()
    data object StopChoosingLanguage : TranslateEvent()
    data object SwapLanguages : TranslateEvent()
    data class ChangeTranslationText(val text: String) : TranslateEvent()
    data object Translate : TranslateEvent()
    data object OpenFromLanguageDropdown : TranslateEvent()
    data object OpenToLanguageDropdown : TranslateEvent()
    data object CloseTranslation : TranslateEvent()
    data class SelectHistoryItem(val historyItem: UiHistoryItem) : TranslateEvent()
    data object EditTranslation : TranslateEvent()
    data object RecordAudio : TranslateEvent()
    data class SubmitVoiceResult(val result: String) : TranslateEvent()
    data object OnErrorSeen : TranslateEvent()
}