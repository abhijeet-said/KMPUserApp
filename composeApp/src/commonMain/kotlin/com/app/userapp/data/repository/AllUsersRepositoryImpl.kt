package com.app.userapp.data.repository

import com.app.userapp.data.model.AllUsersResponse
import com.app.userapp.data.remote.NetworkResult
import com.app.userapp.data.remote.RemoteDataSource
import com.app.userapp.domain.repository.AllUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for fetching data[AllUsersResponse] from server
 * */
class AllUsersRepositoryImpl(private val remoteDataSource: RemoteDataSource) : AllUsersRepository {
    override suspend fun allUsers(): Flow<NetworkResult<AllUsersResponse>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val response = remoteDataSource.allUsers()
            emit(response)
        }
    }
}