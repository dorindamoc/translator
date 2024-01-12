//
//  MicrophonePowerObserver.swift
//  iosApp
//
//  Created by Dorin Damoc on 12/01/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import Speech
import Combine

class MicrphonePowerObserver: ObservableObject {
    private var cancellable: AnyCancellable? = nil
    private var audioRecorder: AVAudioRecorder? = nil
    
    @Published private(set) var micPowerRatio = 0.0
    
    private let powerRatioEmissionsPerSecond = 20.0
    
    func startObserving() {
        do {
            let recorderSettings: [String: Any] = [
                AVFormatIDKey: NSNumber(value: kAudioFormatAppleLossless),
                AVNumberOfChannelsKey: 1,
            ]
            
            let recorder = try AVAudioRecorder(
                url:  URL(fileURLWithPath: "/dev/null", isDirectory: true),
                settings: recorderSettings)
            recorder.isMeteringEnabled = true
            recorder.record()
            
            self.audioRecorder = recorder
            
            self.cancellable = Timer.publish(
                every: 1.0 / powerRatioEmissionsPerSecond,
                tolerance: 1.0 / powerRatioEmissionsPerSecond,
                on: .main,
                in: .common)
            .autoconnect()
            // Weak self to prevent a memmory leak in case the object is destroyed before the sink is cancelled
            .sink { [weak self] _ in
                recorder.updateMeters()
                let powerOffset = recorder.averagePower(forChannel: 0)
                if powerOffset < -50 {
                    self?.micPowerRatio = 0.0
                } else {
                    let normalizedOffset = CGFloat(50 + powerOffset) / 50
                    self?.micPowerRatio = normalizedOffset
                }
            }
            
        } catch {
            print("An error occured when observing the microphone power: \(error.localizedDescription)")
        }
    }
    func release() {
        self.cancellable = nil
        audioRecorder.stop()
        audioRecorder = nil
        
        self.micPowerRatio = 0.0
    }
}
