package com.app.userapp.presentation.screens.login

import com.app.userapp.data.model.LoginResponse

sealed interface LoginScreenState {
    data object Loading : LoginScreenState

    data object Idle : LoginScreenState

    data class Success(val data: LoginResponse) : LoginScreenState

    data class Error(val errorMessage: String) : LoginScreenState
}