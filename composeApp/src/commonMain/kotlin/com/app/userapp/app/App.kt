package com.app.userapp.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.app.userapp.AppTheme
import com.app.userapp.di.init
import com.app.userapp.presentation.screens.splash.SplashScreen
import org.koin.compose.KoinApplication

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    KoinApplication(application = {
        init()
    }) {
        AppTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
            Navigator(SplashScreen)
        }
    }
}