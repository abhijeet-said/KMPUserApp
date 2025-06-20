package com.app.userapp.data.remote

import com.app.userapp.data.model.AllUsersResponse
import com.app.userapp.data.model.LoginResponse

abstract class RemoteDataSource {
    abstract suspend fun login(
        username: String,
        password: String
    ): NetworkResult<LoginResponse>

    abstract suspend fun allUsers(): NetworkResult<AllUsersResponse>
}