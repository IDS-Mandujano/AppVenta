package com.example.api

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.appventa.R

class ApiClient(context: Context) {

    private val dbHelper = SheinDB(context)

    companion object {
        fun pantallaCompleta(activity: AppCompatActivity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity.window.apply {
                    statusBarColor = activity.resources.getColor(R.color.white, null)
                    navigationBarColor = activity.resources.getColor(R.color.white, null)
                }
            }

            activity.window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
    }

    fun registrarUsuario(nombre: String, correo: String, password: String, callback: (Boolean, String) -> Unit) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(SheinDB.COLUMN_NOMBRE, nombre)
            put(SheinDB.COLUMN_CORREO, correo)
            put(SheinDB.COLUMN_PASSWORD, password)
        }

        val newRowId = db.insert(SheinDB.TABLE_USUARIOS, null, values)

        if (newRowId != -1L) {
            callback(true, "Usuario registrado con éxito")
        } else {
            callback(false, "Error al registrar usuario")
        }

        db.close()
    }

    fun loginUsuario(correo: String, password: String, callback: (Boolean, String) -> Unit) {
        val db = dbHelper.readableDatabase

        val cursor: Cursor = db.query(
            SheinDB.TABLE_USUARIOS,
            arrayOf(SheinDB.COLUMN_ID, SheinDB.COLUMN_NOMBRE, SheinDB.COLUMN_CORREO, SheinDB.COLUMN_PASSWORD),
            "${SheinDB.COLUMN_CORREO} = ? AND ${SheinDB.COLUMN_PASSWORD} = ?",  // Condición
            arrayOf(correo, password),  // Argumentos
            null, null, null
        )

        if (cursor.moveToFirst()) {
            callback(true, "Inicio de sesión exitoso")
        } else {
            callback(false, "Correo o contraseña incorrectos")
        }

        cursor.close()
        db.close()
    }

    fun obtenerPedidos(callback: (List<Pedido>) -> Unit) {
        val db = dbHelper.readableDatabase
        val pedidos = mutableListOf<Pedido>()

        val cursor = db.query(
            SheinDB.TABLE_PRODUCTOS,
            arrayOf(
                SheinDB.COLUMN_PRODUCTO_ID,
                SheinDB.COLUMN_PRODUCTO_NOMBRE,
                SheinDB.COLUMN_PRODUCTO_DESCRIPCION,
                SheinDB.COLUMN_PRODUCTO_TOTAL
            ),
            null, null, null, null, null
        )

        if (cursor.moveToFirst()) {
            do {
                val pedido = Pedido(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(SheinDB.COLUMN_PRODUCTO_ID)),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow(SheinDB.COLUMN_PRODUCTO_NOMBRE)),
                    descripcion = cursor.getString(cursor.getColumnIndexOrThrow(SheinDB.COLUMN_PRODUCTO_DESCRIPCION)),
                    total = cursor.getString(cursor.getColumnIndexOrThrow(SheinDB.COLUMN_PRODUCTO_TOTAL)).ensureDecimalFormat().toDouble()
                )
                pedidos.add(pedido) // Agregar a la lista
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        callback(pedidos)
    }

    fun String.ensureDecimalFormat(): String {
        return if (!this.contains(".")) {
            "$this.00" // Si no contiene un punto, agregamos ".00".
        } else if (this.endsWith(".")) {
            "$this 0" // Si termina en un punto, agregamos un 0.
        } else {
            this // Si ya tiene formato correcto, lo dejamos igual.
        }
    }



}