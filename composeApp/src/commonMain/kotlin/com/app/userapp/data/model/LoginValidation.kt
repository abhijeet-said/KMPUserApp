package com.app.userapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginValidation(
    val input:String = "",
    val isError:Boolean = false,
    val errorText:String= ""
)