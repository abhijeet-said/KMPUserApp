package com.app.userapp.data.repository

import com.app.userapp.data.model.LoginResponse
import com.app.userapp.data.remote.NetworkResult
import com.app.userapp.data.remote.RemoteDataSource
import com.app.userapp.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for fetching data[LoginResponse] from server
 * */
class LoginRepositoryImpl(private val remoteDataSource: RemoteDataSource) : LoginRepository {
    override suspend fun loginUser(
        username: String,
        password: String
    ): Flow<NetworkResult<LoginResponse>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val response = remoteDataSource.login(username,password)
            emit(response)
        }
    }

}