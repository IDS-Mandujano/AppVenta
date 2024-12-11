package com.example.api

data class Pedido(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val total: Double,
    val codigo : String,
    val empresa : String,
    val fecha : String
)
