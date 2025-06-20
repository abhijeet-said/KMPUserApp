package com.app.userapp.presentation.screens.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.app.userapp.presentation.screens.login.LoginScreen
import kmpuserapp.composeapp.generated.resources.KMPUserApp
import kmpuserapp.composeapp.generated.resources.Res
import kmpuserapp.composeapp.generated.resources.user_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

object SplashScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { SplashViewModel() }
        val navigation = screenModel.navigation.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(navigation.value) {
            when (navigation.value) {
                is SplashNavigation.ToMain -> {
                    navigator.replace(LoginScreen)
                    screenModel.resetNavigation()
                }
                null -> Unit
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(Res.string.KMPUserApp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))
                Image(
                    painter = painterResource(Res.drawable.user_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                CircularProgressIndicator()
            }
        }
    }
}