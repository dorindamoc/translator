//
//  GradienSurface.swift
//  iosApp
//
//  Created by Dorin Damoc on 09/01/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct GradienSurface: ViewModifier {
    @Environment(\.colorScheme) var colorScheme
    
    func body(content: Content) -> some View {
        if colorScheme == .dark {
            content
                .background(
                    LinearGradient(
                        gradient: Gradient(colors: [Color.gradientStart, Color.gradientEnd]),
                        startPoint: .top, endPoint: .bottom)
                )
        } else {
            content
                .background(
                    Color.surface
                )
        }
    }
}


extension View {
    func gradientSurface() -> some View {
        modifier(GradienSurface())
    }
}
