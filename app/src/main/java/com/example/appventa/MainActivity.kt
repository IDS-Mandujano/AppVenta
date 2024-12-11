package com.example.appventa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.api.ApiClient
import android.view.View; //

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiClient.pantallaCompleta(this) // patalla completa

        val dbHelper = ApiClient(this) // Instancia de la clase

        val btnLogin = findViewById<Button>(R.id.ButtonIniciarSesion)
        val textCorreo = findViewById<EditText>(R.id.TextUsuario)
        val textPassword = findViewById<EditText>(R.id.TextPassword)
        val btnRegistrar = findViewById<Button>(R.id.ButtonIrRegistrarse)

        btnLogin.setOnClickListener {
            val correo = textCorreo.text.toString()
            val password = textPassword.text.toString()

            if (correo.isNotEmpty() && password.isNotEmpty()) {
                dbHelper.loginUsuario(correo, password) { success, message ->
                    runOnUiThread {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        if (success) {
                            //navegación a la siguiente actividad si el login es exitoso
                            val intent = Intent(this, menu::class.java)
                            startActivity(intent)

                        }
                    }
                }
            } else {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegistrar.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }
}
