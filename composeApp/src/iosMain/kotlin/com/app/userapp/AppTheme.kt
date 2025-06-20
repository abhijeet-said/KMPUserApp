package com.app.userapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.app.userapp.util.theme.*

@Composable
actual fun AppTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
){
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}