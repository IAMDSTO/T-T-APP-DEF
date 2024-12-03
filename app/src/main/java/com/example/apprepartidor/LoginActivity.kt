package com.example.apprepartidor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apprepartidor.models.LoginRequest
import com.example.apprepartidor.models.LoginResponse
import com.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ttdef.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias a los elementos del layout
        val emailEditText: EditText = findViewById(R.id.email)
        val passwordEditText: EditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.login_button)

        // Configuración del botón de inicio de sesión
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Validación de campos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // Llamar al método para iniciar sesión
                loginUser(email, password)
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        // Obtener instancia del servicio API
        val apiService = RetrofitClient.apiService
        val loginRequest = LoginRequest(email, password)

        // Llamada a la API
        apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.success) {
                        // Verificar si el usuario tiene el rol de "Repartidor" (rolID == 2)
                        if (loginResponse.data?.rolID?.toInt() == 2) {
                            // Guardar datos en la sesión
                            Session.userId = loginResponse.data?.id
                            Session.userName = loginResponse.data?.nombreUsuario

                            // Redirigir a ListaDePedidos
                            val intent = Intent(this@LoginActivity, ListaDePedidos::class.java)
                            startActivity(intent)
                            finish() // Terminar la actividad actual
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Usuario no autorizado como repartidor",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Credenciales incorrectas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Error en la respuesta del servidor: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Manejo de errores en la conexión
                Toast.makeText(
                    this@LoginActivity,
                    "Error en la conexión: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
