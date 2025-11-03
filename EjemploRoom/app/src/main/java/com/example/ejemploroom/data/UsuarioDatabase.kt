package com.example.ejemploroom.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ejemploroom.model.Usuario

@Database(entities = [Usuario::class], version = 2)
abstract class UsuarioDatabase : RoomDatabase(){
    abstract fun usuarioDao() : UsuarioDao
}