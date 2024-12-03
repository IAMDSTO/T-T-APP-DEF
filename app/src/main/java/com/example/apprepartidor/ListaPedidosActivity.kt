package com.example.apprepartidor.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apprepartidor.Session
import com.example.ttdef.R
import com.example.apprepartidor.models.Pedido
import com.example.apprepartidor.models.PedidoUpdateRequest
import com.example.apprepartidor.models.UserId
import com.example.apprepartidor.ui.adapters.PedidoAdapter
import com.example.apprepartidor.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_lista_pedidos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaPedidosActivity : AppCompatActivity() {

    private var pedidoSeleccionado: Pedido? = null
    private lateinit var pedidoAdapter: PedidoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pedidos)

        setupRecyclerView()
        obtenerPedidos()
        setupListeners()
    }

    private fun setupRecyclerView() {
        pedidoAdapter = PedidoAdapter(emptyList()) { pedido ->
            pedidoSeleccionado = pedido
            Toast.makeText(this, "Pedido seleccionado: ${pedido.descripcion}", Toast.LENGTH_SHORT).show()
        }
        recyclerViewPedidos.layoutManager = LinearLayoutManager(this)
        recyclerViewPedidos.adapter = pedidoAdapter
    }

    private fun setupListeners() {
        boton_actualizar_std.setOnClickListener { actualizarEstado() }
        _boton_pqt_extraviado.setOnClickListener { marcarComoExtraviado() }
    }

    private fun obtenerPedidos() {
        val userId = Session.userId ?: return
        val request = UserId(user_id = userId)

        RetrofitClient.apiService.obtenerPedidos(request).enqueue(object : Callback<List<Pedido>> {
            override fun onResponse(call: Call<List<Pedido>>, response: Response<List<Pedido>>) {
                if (response.isSuccessful) {
                    val pedidos = response.body() ?: emptyList()
                    pedidoAdapter.updateData(pedidos)
                } else {
                    Toast.makeText(this@ListaPedidosActivity, "Error al obtener pedidos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Pedido>>, t: Throwable) {
                Toast.makeText(this@ListaPedidosActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun actualizarEstado() {
        val pedido = pedidoSeleccionado ?: return
        val nuevoEstado = spinnerEstado2.selectedItem.toString()

        RetrofitClient.apiService.actualizarPedido(PedidoUpdateRequest(pedido.id, nuevoEstado))
            .enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        Toast.makeText(this@ListaPedidosActivity, "Estado actualizado", Toast.LENGTH_SHORT).show()
                        obtenerPedidos()
                    } else {
                        Toast.makeText(this@ListaPedidosActivity, "Error al actualizar", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(this@ListaPedidosActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun marcarComoExtraviado() {
        val pedido = pedidoSeleccionado ?: return
        RetrofitClient.apiService.actualizarPedido(PedidoUpdateRequest(pedido.id, "Extraviado"))
            .enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        Toast.makeText(this@ListaPedidosActivity, "Pedido marcado como extraviado", Toast.LENGTH_SHORT).show()
                        obtenerPedidos()
                    } else {
                        Toast.makeText(this@ListaPedidosActivity, "Error al marcar como extraviado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(this@ListaPedidosActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
package com.example.apprepartidor.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apprepartidor.Session
import com.example.ttdef.R
import com.example.apprepartidor.models.Pedido
import com.example.apprepartidor.models.PedidoUpdateRequest
import com.example.apprepartidor.models.UserId
import com.example.apprepartidor.ui.adapters.PedidoAdapter
import com.example.apprepartidor.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_lista_pedidos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaPedidosActivity : AppCompatActivity() {

    private var pedidoSeleccionado: Pedido? = null
    private lateinit var pedidoAdapter: PedidoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pedidos)

        setupRecyclerView()
        obtenerPedidos()
        setupListeners()
    }

    private fun setupRecyclerView() {
        pedidoAdapter = PedidoAdapter(emptyList()) { pedido ->
            pedidoSeleccionado = pedido
            Toast.makeText(this, "Pedido seleccionado: ${pedido.descripcion}", Toast.LENGTH_SHORT).show()
        }
        recyclerViewPedidos.layoutManager = LinearLayoutManager(this)
        recyclerViewPedidos.adapter = pedidoAdapter
    }

    private fun setupListeners() {
        boton_actualizar_std.setOnClickListener { actualizarEstado() }
        _boton_pqt_extraviado.setOnClickListener { marcarComoExtraviado() }
    }

    private fun obtenerPedidos() {
        val userId = Session.userId ?: return
        val request = UserId(user_id = userId)

        RetrofitClient.apiService.obtenerPedidos(request).enqueue(object : Callback<List<Pedido>> {
            override fun onResponse(call: Call<List<Pedido>>, response: Response<List<Pedido>>) {
                if (response.isSuccessful) {
                    val pedidos = response.body() ?: emptyList()
                    pedidoAdapter.updateData(pedidos)
                } else {
                    Toast.makeText(this@ListaPedidosActivity, "Error al obtener pedidos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Pedido>>, t: Throwable) {
                Toast.makeText(this@ListaPedidosActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun actualizarEstado() {
        val pedido = pedidoSeleccionado ?: return
        val nuevoEstado = spinnerEstado2.selectedItem.toString()

        RetrofitClient.apiService.actualizarPedido(PedidoUpdateRequest(pedido.id, nuevoEstado))
            .enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        Toast.makeText(this@ListaPedidosActivity, "Estado actualizado", Toast.LENGTH_SHORT).show()
                        obtenerPedidos()
                    } else {
                        Toast.makeText(this@ListaPedidosActivity, "Error al actualizar", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(this@ListaPedidosActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun marcarComoExtraviado() {
        val pedido = pedidoSeleccionado ?: return
        RetrofitClient.apiService.actualizarPedido(PedidoUpdateRequest(pedido.id, "Extraviado"))
            .enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        Toast.makeText(this@ListaPedidosActivity, "Pedido marcado como extraviado", Toast.LENGTH_SHORT).show()
                        obtenerPedidos()
                    } else {
                        Toast.makeText(this@ListaPedidosActivity, "Error al marcar como extraviado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(this@ListaPedidosActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
