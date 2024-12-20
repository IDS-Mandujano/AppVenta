package com.example.api

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SheinDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "productos.db"
        private const val DATABASE_VERSION = 3

        const val TABLE_USUARIOS = "usuarios"
        const val TABLE_PRODUCTOS = "productos"

        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_CORREO = "correo"
        const val COLUMN_PASSWORD = "password"

        const val COLUMN_PRODUCTO_ID = "producto_id"
        const val COLUMN_PRODUCTO_NOMBRE = "producto_nombre"
        const val COLUMN_PRODUCTO_DESCRIPCION = "producto_descripcion"
        const val COLUMN_PRODUCTO_TOTAL = "producto_total"
    }

    private val CREATE_TABLE_USUARIOS = """
        CREATE TABLE $TABLE_USUARIOS (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
            $COLUMN_NOMBRE TEXT, 
            $COLUMN_CORREO TEXT, 
            $COLUMN_PASSWORD TEXT);
    """

    private val CREATE_TABLE_PRODUCTOS = """
        CREATE TABLE $TABLE_PRODUCTOS (
            $COLUMN_PRODUCTO_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
            $COLUMN_PRODUCTO_NOMBRE TEXT, 
            $COLUMN_PRODUCTO_DESCRIPCION REAL, 
            $COLUMN_PRODUCTO_TOTAL TEXT);
    """

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USUARIOS)
        db?.execSQL(CREATE_TABLE_PRODUCTOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTOS")
        onCreate(db)
    }

    fun insertarProducto(nombre: String, descripcion: String, total: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_PRODUCTO_NOMBRE, nombre)
            put(COLUMN_PRODUCTO_DESCRIPCION, descripcion)
            put(COLUMN_PRODUCTO_TOTAL, total)
        }
        val result = db.insert(TABLE_PRODUCTOS, null, values)
        if (result == -1L) {
            Log.e("SheinDB", "Error al insertar producto")
        } else {
            Log.d("SheinDB", "Producto insertado con éxito, ID: $result")
        }
        db.close()
    }

}