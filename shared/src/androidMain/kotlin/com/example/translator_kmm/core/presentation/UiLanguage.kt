package com.example.translator_kmm.core.presentation

import androidx.annotation.DrawableRes
import com.example.translator_kmm.R
import com.example.translator_kmm.core.domain.language.Language
import java.util.Locale

actual class UiLanguage(
    @DrawableRes val drawableRes: Int,
    actual val language: Language
) {
    fun toLocale(): Locale? {
        return when(language) {
            Language.ENGLISH -> Locale.ENGLISH
            Language.ARABIC -> Locale("ar")
            Language.AZERBAIJANI -> Locale("az")
            Language.CHINESE -> Locale.CHINESE
            Language.CZECH -> Locale("cs")
            Language.DANISH -> Locale("da")
            Language.DUTCH -> Locale("nl")
            Language.FINNISH -> Locale("fi")
            Language.FRENCH -> Locale.FRENCH
            Language.GERMAN -> Locale.GERMAN
            Language.GREEK -> Locale("el")
            Language.HEBREW -> Locale("he")
            Language.HINDI -> Locale("hi")
            Language.HUNGARIAN -> Locale("hu")
            Language.INDONESIAN -> Locale("in")
            Language.IRISH -> Locale("ga")
            Language.ITALIAN -> Locale.ITALIAN
            Language.JAPANESE -> Locale.JAPANESE
            Language.KOREAN -> Locale.KOREAN
            Language.PERSIAN -> Locale("fa")
            Language.POLISH -> Locale("pl")
            Language.PORTUGUESE -> Locale("pt")
            Language.RUSSIAN -> Locale("ru")
            Language.SLOVAK -> Locale("sk")
            Language.SPANISH -> Locale("es")
            Language.SWEDISH -> Locale("sv")
            Language.TURKISH -> Locale("tr")
            Language.UKRAINIAN -> Locale("uk")
            else -> null
        }
    }
    actual companion object {
        actual fun byCode(langCode: String): UiLanguage {
            return allLanguages.find { it.language.langCode == langCode }
                ?: throw IllegalArgumentException("Language with code $langCode not found")
        }

        actual val allLanguages: List<UiLanguage>
            get() = Language.entries.map { language ->
                UiLanguage(
                    language = language,
                    drawableRes = when (language) {
                        Language.ENGLISH -> R.drawable.english
                        Language.ARABIC -> R.drawable.arabic
                        Language.AZERBAIJANI -> R.drawable.azerbaijani
                        Language.CHINESE -> R.drawable.chinese
                        Language.CZECH -> R.drawable.czech
                        Language.DANISH -> R.drawable.danish
                        Language.DUTCH -> R.drawable.dutch
                        Language.FINNISH -> R.drawable.finnish
                        Language.FRENCH -> R.drawable.french
                        Language.GERMAN -> R.drawable.german
                        Language.GREEK -> R.drawable.greek
                        Language.HEBREW -> R.drawable.hebrew
                        Language.HINDI -> R.drawable.hindi
                        Language.HUNGARIAN -> R.drawable.hungarian
                        Language.INDONESIAN -> R.drawable.indonesian
                        Language.IRISH -> R.drawable.irish
                        Language.ITALIAN -> R.drawable.italian
                        Language.JAPANESE -> R.drawable.japanese
                        Language.KOREAN -> R.drawable.korean
                        Language.PERSIAN -> R.drawable.persian
                        Language.POLISH -> R.drawable.polish
                        Language.PORTUGUESE -> R.drawable.portuguese
                        Language.RUSSIAN -> R.drawable.russian
                        Language.SLOVAK -> R.drawable.slovak
                        Language.SPANISH -> R.drawable.spanish
                        Language.SWEDISH -> R.drawable.swedish
                        Language.TURKISH -> R.drawable.turkish
                        Language.UKRAINIAN -> R.drawable.ukrainian
                    }
                )
            }
                .sortedBy { it.language.name }
    }
}