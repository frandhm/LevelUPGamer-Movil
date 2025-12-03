package com.example.ejemploroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Producto")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val precio: String,
    val stock: Int,
    val imagenUrl: String
)