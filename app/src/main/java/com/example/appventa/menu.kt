package com.example.appventa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnDatos: Button = findViewById(R.id.ButtonCatalogo)
        val btnPedidos : Button = findViewById(R.id.ButtonDatos)

        btnDatos.setOnClickListener {
            val intent = Intent(this, add_producto::class.java)
            startActivity(intent)
        }

        btnPedidos.setOnClickListener {
            val  intent = Intent(this, pedidos_screen::class.java)
            startActivity(intent)
        }


    }
}
