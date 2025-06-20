package com.app.userapp.domain.usecase

import com.app.userapp.data.model.LoginResponse
import com.app.userapp.data.remote.NetworkResult
import com.app.userapp.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

/**
 * An interactor class that executes the implementation of [LoginViewModel]
 * It handles user authentication
 */
class LoginUseCase(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(username: String, pass: String): Flow<NetworkResult<LoginResponse>> {
        return loginRepository.loginUser(username, pass)
    }
}