package com.app.userapp.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.app.userapp.core.ViewModel
import com.app.userapp.data.model.LoginValidation
import com.app.userapp.data.remote.ApiStatus
import com.app.userapp.domain.usecase.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel class for managing and preparing data for UI [LoginScreen].
 *
 * @author Waleed
 **/

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel {
    //below are the by default credentials for login
    var userNameInput by mutableStateOf(LoginValidation("jamesd"))
    var passwordInput by mutableStateOf(LoginValidation("jamesdpass"))

    private val _state = MutableStateFlow<LoginScreenState>(LoginScreenState.Idle)
    var state = _state.asStateFlow()

    fun loginUser(username: String, pass: String) {
        if (userNameInput.input.isEmpty() || userNameInput.input.isBlank()) {
            userNameInput = userNameInput.copy(isError = true, errorText = "Please Enter Username")
            return
        }
        if (passwordInput.input.isEmpty() || passwordInput.input.isBlank()) {
            passwordInput = passwordInput.copy(isError = true, errorText = "Please Enter Password")
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            loginUseCase.invoke(username, pass).collect { response ->
                when (response.status) {
                    ApiStatus.LOADING -> {
                        _state.update {
                            LoginScreenState.Loading
                        }
                    }

                    ApiStatus.SUCCESS -> {
                        response.data?.let {
                            _state.update {
                                LoginScreenState.Success(response.data)
                            }
                        } ?: run {
                            _state.update {
                                LoginScreenState.Error("Unknown error occurred")
                            }
                        }
                    }

                    ApiStatus.ERROR -> {
                        _state.update {
                            LoginScreenState.Error(response.message.toString())
                        }
                    }
                }

            }
        }
    }

    fun resetState(){
        _state.update {
            LoginScreenState.Idle
        }
    }

}