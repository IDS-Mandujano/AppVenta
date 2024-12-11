package com.example.appventa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.api.SheinDB

class add_producto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_producto)


        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val etTotal = findViewById<EditText>(R.id.etTotal)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)



        btnGuardar.setOnClickListener {

            val nombre = etNombre.text.toString()
            val descripcion = etDescripcion.text.toString()
            val total = etTotal.text.toString()

            if (nombre.isNotEmpty() && descripcion.isNotEmpty() && total.isNotEmpty()) {
                // Convertir total a Double
                val totalDouble = total.toDouble()
                registrarProducto(nombre, descripcion, totalDouble)
                intentToHome()
            } else {
            }
        }
    }

    private fun intentToHome(){
        val intent = Intent(this, menu::class.java)
        startActivity(intent)
    }

    fun registrarProducto( nombre: String, descripcion: String, total: Double) {
        val dbHelper = SheinDB(this)
        dbHelper.insertarProducto(nombre, descripcion, total.toString()) // Asegúrate de que la función insertarProducto acepte el 'numero'
        Toast.makeText(this, "Producto registrado con éxito", Toast.LENGTH_SHORT).show()
    }
}