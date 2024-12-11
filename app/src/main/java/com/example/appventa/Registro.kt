package com.example.appventa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.api.ApiClient

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        ApiClient.pantallaCompleta(this)

        val dbHelper = ApiClient(this)

        val btnRegister = findViewById<Button>(R.id.ButtonRegistrarseR)
        val etName = findViewById<EditText>(R.id.TextUsuarioR)
        val etEmail = findViewById<EditText>(R.id.TextCorreoR)
        val etPassword = findViewById<EditText>(R.id.TextPasswordR)

        btnRegister.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                dbHelper.registrarUsuario(name, email, password) { success, message ->
                    runOnUiThread {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        if (success) finish()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            } else {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
