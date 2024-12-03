package com.example.apprepartidor.models

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: Usuario?
)