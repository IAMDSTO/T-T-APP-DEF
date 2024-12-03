package com.example.apprepartidor.models

data class Usuario(
    val idUsuario: Long,
    val id: Long,
    val nombreUsuario: String,
    val email: String,
    val passwordHash: String,
    val rolID: Long,
    val nombreRol: String,
    val estadoCuenta: String?,
    val fechaCreacion: String
)