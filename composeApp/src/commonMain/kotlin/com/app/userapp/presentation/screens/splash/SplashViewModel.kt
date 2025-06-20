package com.app.userapp.presentation.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.app.userapp.core.ViewModel
import com.app.userapp.presentation.screens.login.LoginScreen
import com.app.userapp.util.extensions.currentNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SplashNavigation {
    data object ToMain : SplashNavigation()
}

class SplashViewModel : ViewModel {
    private val _navigation = MutableStateFlow<SplashNavigation?>(null)
    val navigation: StateFlow<SplashNavigation?> get() = _navigation

    init {
        // Simulate startup logic, like auth or loading config
        screenModelScope.launch {
            delay(2000) // simulate splash delay
            _navigation.value = SplashNavigation.ToMain
        }
    }

    fun resetNavigation() {
        _navigation.value = null
    }
}