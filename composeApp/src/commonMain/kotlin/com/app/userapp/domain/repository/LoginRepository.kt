package com.app.userapp.domain.repository

import com.app.userapp.data.model.LoginResponse
import com.app.userapp.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * interface to make an interaction between [LoginRepositoryImpl] & [LoginUseCase]
 * */
interface LoginRepository {
    suspend fun loginUser(username: String, password: String): Flow<NetworkResult<LoginResponse>>
}