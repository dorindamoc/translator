package com.example.translator_kmm.core.presentation

import com.example.translator_kmm.core.domain.language.Language

// Needs to be an expect because the way to retrieve vector drawables is different on android and ios
expect class UiLanguage {
    val language: Language
    companion object {
        fun byCode(langCode: String): UiLanguage
        val allLanguages: List<UiLanguage>
    }
}