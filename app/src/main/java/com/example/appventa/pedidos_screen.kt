package com.example.appventa

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.ApiClient

class pedidos_screen : AppCompatActivity() {

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedidos_screen)

        context = this

        val recyclerView: RecyclerView = findViewById(R.id.pedidosRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val apiClient = ApiClient(context)

        apiClient.obtenerPedidos { pedidos ->
            if (pedidos.isNotEmpty()) {
                val adapter = PedidoAdapter(pedidos)


                Toast.makeText(this, "${pedidos}", Toast.LENGTH_LONG).show()


                recyclerView.adapter = adapter
            } else {
                Log.d("pedidos_screen", "No hay pedidos para mostrar")
            }
        }
    }
}