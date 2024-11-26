package com.tuapp.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("NombreUsuario") val nombreUsuario: String,
    @SerializedName("Email") val email: String,
    @SerializedName("RolID") val rolId: Int,
    @SerializedName("NombreRol") val nombreRol: String,
    @SerializedName("EstadoCuenta") val estadoCuenta: String,
    @SerializedName("error") val error: String? = null
)
