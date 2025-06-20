package com.app.userapp

import androidx.compose.ui.window.ComposeUIViewController
import com.app.userapp.app.App
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme = UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
            UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(darkTheme = isDarkTheme, dynamicColor = false)
}