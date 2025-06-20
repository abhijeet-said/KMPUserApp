package com.app.userapp.presentation.screens.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import com.app.userapp.core.BackHandler
import com.app.userapp.core.viewModel
import com.app.userapp.data.model.UserData
import com.app.userapp.presentation.components.AppScaffold
import com.app.userapp.presentation.components.ErrorScreen
import com.app.userapp.presentation.components.ProgressIndicator
import com.app.userapp.presentation.components.UserItem
import kmpuserapp.composeapp.generated.resources.AllUserHeader
import kmpuserapp.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource

object AllUsersScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: AllUsersViewModel = viewModel()
        MainContent(viewModel)
    }

    @Composable
    fun MainContent(viewModel: AllUsersViewModel) {
        val usersState by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.showAllUsers()
        }

        Column {
            when (usersState) {
                is AllUsersScreenState.Loading -> {
                    ProgressIndicator()
                }

                is AllUsersScreenState.Idle -> {}

                is AllUsersScreenState.Success -> {
                    AllUserList(
                        (usersState as AllUsersScreenState.Success).data.users
                    )
                }

                is AllUsersScreenState.Error -> {
                    ErrorScreen((usersState as AllUsersScreenState.Error).errorMessage)
                }
            }
        }

        BackHandler(false, onBack = {
        })
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AllUserList(userList: List<UserData>) {
        AppScaffold(
            topAppBar = {
                TopAppBar(title = {
                    Text(
                        text = stringResource(Res.string.AllUserHeader),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                })
            },
            content = { contentPadding ->
                LazyColumn(
                    contentPadding = contentPadding,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(userList) { user ->
                        UserItem(user, onUserClick = {
                        })
                    }
                }
            }
        )

    }
}