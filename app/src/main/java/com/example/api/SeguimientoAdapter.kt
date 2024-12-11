package com.example.appventa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api.Pedido

class SeguimientoAdapter(private val seguimientos: List<Pedido>) : RecyclerView.Adapter<SeguimientoAdapter.SeguimientoViewHolder>() {

    class SeguimientoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.id_producto)
        val codigoTextView: TextView = itemView.findViewById(R.id.codigoView)
        val empresaTextView: TextView = itemView.findViewById(R.id.empresaView)
        val fechaTextView: TextView = itemView.findViewById(R.id.dateView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeguimientoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.seguimiento, parent, false)
        return SeguimientoViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeguimientoViewHolder, position: Int) {
        val seguimiento = seguimientos[position]
        holder.idTextView.text = seguimiento.id.toString() // Set ID
        holder.codigoTextView.text = seguimiento.codigo // Set Código
        holder.empresaTextView.text = seguimiento.empresa // Set Empresa
        holder.fechaTextView.text = seguimiento.fecha // Set Fecha Entrega
    }

    override fun getItemCount(): Int = seguimientos.size
}