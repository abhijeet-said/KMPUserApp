package com.app.userapp.domain.usecase

import com.app.userapp.data.model.AllUsersResponse
import com.app.userapp.data.remote.NetworkResult
import com.app.userapp.domain.repository.AllUsersRepository
import kotlinx.coroutines.flow.Flow

/**
 * An interactor class that executes the implementation of [AllUsersViewModel]
 * It handles data responses and manages a list of users.
 */

class AllUsersUseCase(private val allUsersRepository: AllUsersRepository) {
    suspend operator fun invoke(): Flow<NetworkResult<AllUsersResponse>> {
        return allUsersRepository.allUsers()
    }
}