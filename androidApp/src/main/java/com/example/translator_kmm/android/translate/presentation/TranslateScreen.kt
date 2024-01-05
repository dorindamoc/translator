package com.example.translator_kmm.android.translate.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.translator_kmm.android.translate.presentation.components.LanguageDropDown
import com.example.translator_kmm.android.translate.presentation.components.SwapLanguagesButton
import com.example.translator_kmm.core.presentation.TranslateEvent
import com.example.translator_kmm.core.presentation.TranslateState

@Composable
fun TranslateScreen(
    state: TranslateState, onEvent: (TranslateEvent) -> Unit
) {
    Scaffold(floatingActionButton = {}) { padding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        LanguageDropDown(language = state.fromLanguage,
                            isOpen = state.isChoosingFromLanguage,
                            onClick = { onEvent(TranslateEvent.OpenFromLanguageDropdown) },
                            onDismiss = { onEvent(TranslateEvent.StopChoosingLanguage) },
                            onSelectedLanguage = {
                                onEvent(TranslateEvent.ChooseFromLanguage(it))
                            })
                        Spacer(modifier = Modifier.weight(1f))
                        SwapLanguagesButton(onClick = { onEvent(TranslateEvent.SwapLanguages) })
                        Spacer(modifier = Modifier.weight(1f))
                        LanguageDropDown(language = state.toLanguage,
                            isOpen = state.isChoosingToLanguage,
                            onClick = { onEvent(TranslateEvent.OpenToLanguageDropdown) },
                            onDismiss = { onEvent(TranslateEvent.StopChoosingLanguage) },
                            onSelectedLanguage = {
                                onEvent(TranslateEvent.ChooseToLanguage(it))
                            })
                    }
                }
            })
    }
}