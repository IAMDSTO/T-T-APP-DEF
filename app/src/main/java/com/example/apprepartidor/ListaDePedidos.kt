package com.example.apprepartidor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ttdef.R

class ListaDePedidos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pedidos)

        // Botón para actualizar estado
        val botonActualizarStd: Button = findViewById(R.id.boton_actualizar_std)
        botonActualizarStd.setOnClickListener {
            // Navegar a la actividad de actualizar pedido
            val intent = Intent(this, ActualizarPedidoActivity::class.java)
            startActivity(intent)
        }

        // Botón para reportar extravío
        val botonPqtExtraviado: Button = findViewById(R.id._boton_pqt_extraviado)
        botonPqtExtraviado.setOnClickListener {
            // Navegar a la actividad de extravío
            val intent = Intent(this, ExtravioActivity::class.java)
            startActivity(intent)
        }

        // Botón para cerrar sesión, si lo necesitas
        val botonCerrarSesion: Button = findViewById(R.id.boton_cerrar_sesion)
        botonCerrarSesion.setOnClickListener {
            // Aquí puedes agregar la lógica para cerrar sesión
            finish() // Finaliza esta actividad y regresa a la anterior
        }
    }
}
