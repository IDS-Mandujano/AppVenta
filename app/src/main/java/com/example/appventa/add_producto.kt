package com.example.appventa

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

        val etNumero = findViewById<EditText>(R.id.etNumero)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val etTotal = findViewById<EditText>(R.id.etTotal)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)



        btnGuardar.setOnClickListener {
            val numero = etNumero.text.toString()
            val nombre = etNombre.text.toString()
            val descripcion = etDescripcion.text.toString()
            val total = etTotal.text.toString()

            if (nombre.isNotEmpty() && descripcion.isNotEmpty() && total.isNotEmpty()) {
                // Convertir total a Double
                val totalDouble = total.toDouble()
                if (totalDouble != null) {
                    // Llamar a la función con el parámetro 'numero' agregado
                    registrarProducto(numero, nombre, descripcion, totalDouble)
                } else {
                    Toast.makeText(this, "Total no válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun registrarProducto(numero: String, nombre: String, descripcion: String, total: Double) {
        val dbHelper = SheinDB(this)
        dbHelper.insertarProducto(numero, nombre, descripcion, total) // Asegúrate de que la función insertarProducto acepte el 'numero'
        Toast.makeText(this, "Producto registrado con éxito", Toast.LENGTH_SHORT).show()
    }
}