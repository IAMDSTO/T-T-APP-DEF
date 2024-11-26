package com.tuapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ttdef.R
import com.tuapp.api.RetrofitClient
import com.tuapp.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = "admin@gmail.com"
        val password = "123456"

        RetrofitClient.instance.iniciarSesion(email, password).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    if (userResponse?.error == null) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Bienvenido, ${userResponse?.nombreUsuario}",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(this@LoginActivity, userResponse.error, Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Error en la respuesta", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error de red: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
