package com.example.ejemploroom.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ejemploroom.model.Producto

@Database(entities = [Producto::class], version = 1)
abstract class ProductoDatabase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
}