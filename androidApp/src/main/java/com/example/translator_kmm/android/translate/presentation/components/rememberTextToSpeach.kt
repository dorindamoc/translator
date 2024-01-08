package com.example.translator_kmm.android.translate.presentation.components

import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


// Is necessary to create this because the class TextToSpeech has functions that need to be called
// when we don't need this TextToSpeech object anymore. To not cause any leaks when the composable
// leaves the composition it will dispose of the object properly
@Composable
fun rememberTextToSpeach(): TextToSpeech {
    val context = LocalContext.current
    val tts = remember {
        TextToSpeech(context, null)
    }
    DisposableEffect(key1 = tts) {
        onDispose {
            tts.stop()
            tts.shutdown()
        }
    }
    return tts
}