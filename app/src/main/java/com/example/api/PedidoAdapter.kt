package com.example.appventa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api.Pedido

class PedidoAdapter(private val pedidos: List<Pedido>) : RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    class PedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var idTextView: TextView = itemView.findViewById(R.id.id_producto)
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreView)
        val descripcionTextView: TextView = itemView.findViewById(R.id.descripcionView)
        val totalTextView: TextView = itemView.findViewById(R.id.totalView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pedido, parent, false)
        return PedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.idTextView.text = pedido.id.toString() // Asignar el ID
        holder.nombreTextView.text = pedido.nombre // Asignar el nombre
        holder.descripcionTextView.text = pedido.descripcion.toString() // Asignar la descripción
        holder.totalTextView.text = pedido.total.toString() // Asignar el total
    }

    override fun getItemCount(): Int = pedidos.size
}
