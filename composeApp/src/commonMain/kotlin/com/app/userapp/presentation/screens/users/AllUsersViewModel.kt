package com.app.userapp.presentation.screens.users

import com.app.userapp.core.ViewModel
import com.app.userapp.data.remote.ApiStatus
import com.app.userapp.domain.usecase.AllUsersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel class for managing and preparing data for UI [AllUsersScreen].
 *
 * @author Waleed
 **/

class AllUsersViewModel(
    private val allUsersUseCase: AllUsersUseCase
) : ViewModel {

    private val _state = MutableStateFlow<AllUsersScreenState>(AllUsersScreenState.Idle)
    var state = _state.asStateFlow()

    fun showAllUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            allUsersUseCase.invoke().collect { response ->
                when (response.status) {
                    ApiStatus.LOADING -> {
                        _state.update {
                            AllUsersScreenState.Loading
                        }
                    }

                    ApiStatus.SUCCESS -> {
                        response.data?.let {
                            _state.update {
                                AllUsersScreenState.Success(response.data)
                            }
                        } ?: run {
                            _state.update {
                                AllUsersScreenState.Error("Unknown error occurred")
                            }
                        }
                    }

                    ApiStatus.ERROR -> {
                        _state.update {
                            AllUsersScreenState.Error(response.message.toString())
                        }
                    }
                }

            }
        }
    }
}