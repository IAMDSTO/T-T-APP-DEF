package com.example.apprepartidor.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apprepartidor.Session
import com.example.ttdef.R
import com.example.apprepartidor.models.Pedido
import com.example.apprepartidor.models.UserId
import com.example.apprepartidor.ui.adapters.PedidoAdapter
import com.example.ttdef.databinding.ActivityListaPedidosBinding // Importamos el binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaPedidosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaPedidosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Asignamos el binding
        binding = ActivityListaPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtén el userId de la sesión
        val userId = Session.userId ?: return

        // Llamar a la API para obtener los pedidos
        obtenerPedidos(userId)
    }

    private fun obtenerPedidos(userId: Long) {
        // Crea el objeto UserId
        val userIdRequest = UserId(user_id = userId)

        // Llamada a la API usando Retrofit
        RetrofitClient.apiService.obtenerPedidos(userIdRequest)
            .enqueue(object : Callback<List<Pedido>> {
                override fun onResponse(call: Call<List<Pedido>>, response: Response<List<Pedido>>) {
                    if (response.isSuccessful && response.body() != null) {
                        val pedidos = response.body()!!
                        setupRecyclerView(pedidos)
                    } else {
                        Toast.makeText(this@ListaPedidosActivity, "Error al obtener los pedidos", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Pedido>>, t: Throwable) {
                    Toast.makeText(this@ListaPedidosActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun setupRecyclerView(pedidos: List<Pedido>) {
        val adapter = PedidoAdapter(pedidos)
        binding.recyclerViewPedidos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPedidos.adapter = adapter
    }
}
