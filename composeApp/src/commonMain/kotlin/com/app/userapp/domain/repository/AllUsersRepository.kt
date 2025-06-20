package com.app.userapp.domain.repository

import com.app.userapp.data.model.AllUsersResponse
import com.app.userapp.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * interface to make an interaction between [AllUsersRepositoryImpl] & [AllUsersUseCase]
 * */
interface AllUsersRepository {
    suspend fun allUsers(): Flow<NetworkResult<AllUsersResponse>>
}