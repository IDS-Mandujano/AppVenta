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


        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val etTotal = findViewById<EditText>(R.id.etTotal)
        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val etEmpresa = findViewById<EditText>(R.id.etEmpresa)
        val etFecha = findViewById<EditText>(R.id.etFechaEntrega)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)



        btnGuardar.setOnClickListener {

            val nombre = etNombre.text.toString()
            val descripcion = etDescripcion.toString()
            val total = etTotal.text.toString()
            val codigo = etCodigo.text.toString()
            val empresa = etEmpresa.text.toString()
            val fecha = etFecha.text.toString()

            if (nombre.isNotEmpty() && descripcion.isNotEmpty() && total.isNotEmpty() && codigo.isNotEmpty() && empresa.isNotEmpty() && fecha.isNotEmpty()) {
                val totalDouble = total.toDouble()
                registrarProducto(nombre, descripcion, totalDouble, codigo, empresa, fecha)
            } else {
            }
        }
    }

    fun registrarProducto( nombre: String, descripcion: String, total: Double, codigo : String, empresa : String, fecha : String) {
        val dbHelper = SheinDB(this)
        dbHelper.insertarProducto(nombre, descripcion, total.toString(), codigo, empresa, fecha)
        Toast.makeText(this, "Producto registrado con éxito", Toast.LENGTH_SHORT).show()
    }
}