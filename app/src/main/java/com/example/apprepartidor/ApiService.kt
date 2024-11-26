package com.tuapp.api

import com.tuapp.model.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("api/login.php") // Cambia según la ubicación en XAMPP
    fun iniciarSesion(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserResponse>
}
