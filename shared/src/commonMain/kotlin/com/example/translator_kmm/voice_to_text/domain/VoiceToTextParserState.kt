package com.example.translator_kmm.voice_to_text.domain

data class VoiceToTextParserState(
    val result: String = "",
    var error: String? = null,
    val powerRation: Float = 0f,
    val isSpeaking: Boolean = false,
)
