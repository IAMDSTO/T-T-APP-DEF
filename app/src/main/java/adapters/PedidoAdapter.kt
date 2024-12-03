package com.example.apprepartidor.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apprepartidor.models.Pedido
import com.example.ttdef.databinding.ItemPedidoBinding // Importa el ViewBinding de item_pedido.xml

class PedidoAdapter(private val pedidos: List<Pedido>) : RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val binding = ItemPedidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PedidoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.bind(pedido)
    }

    override fun getItemCount(): Int = pedidos.size

    inner class PedidoViewHolder(private val binding: ItemPedidoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pedido: Pedido) {
            binding.tvDescripcion.text = pedido.Descripcion
            binding.tvEstado.text = pedido.EstadoActual
        }
    }
}
