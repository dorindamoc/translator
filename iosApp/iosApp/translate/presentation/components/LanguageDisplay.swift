//
//  LanguageDisplay.swift
//  iosApp
//
//  Created by Dorin Damoc on 09/01/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LanguageDisplay: View {
    var language: UiLanguage
    var body: some View {
        HStack {
            SmallLanguageIcon(language: language)
                .padding(.trailing, 5)
            Text(language.language.langName)
                .foregroundColor(.lightBlue)
        }
    }
}

#Preview {
    LanguageDisplay(language: UiLanguage(language: .german, imageName: "german"))
}
