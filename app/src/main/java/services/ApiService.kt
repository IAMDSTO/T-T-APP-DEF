package com.example.apprepartidor.services

import com.example.apprepartidor.models.LoginResponse
import com.example.apprepartidor.models.LoginRequest
import com.example.apprepartidor.models.Pedido
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("APIAndroid/loginAndroid.php") // Reemplaza con el endpoint de tu servidor
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("APIAndroid/mostrarPedidos.php")

    suspend fun obtenerPedidos(@Body body: Map<String, Any>): Response<List<Pedido>>
}

