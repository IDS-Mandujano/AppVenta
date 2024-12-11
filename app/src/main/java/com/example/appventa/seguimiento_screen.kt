package com.example.appventa

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.ApiClient

class seguimiento_screen : AppCompatActivity() {

    private lateinit var seguimientoAdapter: SeguimientoAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seguimiento_screen)

        recyclerView = findViewById(R.id.seguimientosRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        ApiClient(this).obtenerPedidos { pedidos ->
            if (pedidos.isNotEmpty()) {
                seguimientoAdapter = SeguimientoAdapter(pedidos)
                recyclerView.adapter = seguimientoAdapter
            } else {
                Toast.makeText(this, "No se encontraron pedidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}