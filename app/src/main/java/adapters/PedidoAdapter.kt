package com.example.apprepartidor.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apprepartidor.models.Pedido
import com.example.ttdef.R

class PedidoAdapter(
    private var pedidos: List<Pedido>,
    private val onPedidoClick: (Pedido) -> Unit
) : RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    private var selectedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pedido, parent, false)
        return PedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.bind(pedido, position == selectedPosition)

        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousPosition)
            notifyItemChanged(position)
            onPedidoClick(pedido)
        }
    }

    override fun getItemCount(): Int = pedidos.size

    fun updateData(newPedidos: List<Pedido>) {
        pedidos = newPedidos
        notifyDataSetChanged()
    }

    class PedidoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val descripcion: TextView = view.findViewById(R.id.descripcionTextView)
        private val estado: TextView = view.findViewById(R.id.estadoTextView)

        fun bind(pedido: Pedido, isSelected: Boolean) {
            descripcion.text = pedido.descripcion
            estado.text = pedido.estado
            itemView.isSelected = isSelected
        }
    }
}
