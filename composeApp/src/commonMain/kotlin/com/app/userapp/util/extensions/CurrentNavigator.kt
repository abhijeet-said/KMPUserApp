package com.app.userapp.util.extensions

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

@Composable
fun currentNavigator() : Navigator {
    return LocalNavigator.currentOrThrow.parent?.let { it } ?: LocalNavigator.currentOrThrow
}