package com.app.userapp.presentation.screens.users

import com.app.userapp.data.model.AllUsersResponse

sealed interface AllUsersScreenState {
    data object Loading : AllUsersScreenState

    data object Idle : AllUsersScreenState

    data class Success(val data: AllUsersResponse) : AllUsersScreenState

    data class Error(val errorMessage: String) : AllUsersScreenState
}