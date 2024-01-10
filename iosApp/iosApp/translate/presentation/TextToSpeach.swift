//
//  TextToSpeach.swift
//  iosApp
//
//  Created by Dorin Damoc on 10/01/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import AVFoundation

struct TextToSpeach {
    private let syntesizer = AVSpeechSynthesizer()
    
    func speak (text: String, language: String) {
        let utterance = AVSpeechUtterance(string: text)
        utterance.voice = AVSpeechSynthesisVoice(language: language)
        utterance.volume = 1
        syntesizer.speak(utterance)
    }
}
