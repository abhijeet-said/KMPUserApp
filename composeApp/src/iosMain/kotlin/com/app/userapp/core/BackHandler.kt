package com.app.userapp.core

import androidx.compose.runtime.Composable

@Composable
actual fun BackHandler(isEnabled: Boolean, onBack: () -> Unit) {
    if (isEnabled) {
        onBack()
    }
}