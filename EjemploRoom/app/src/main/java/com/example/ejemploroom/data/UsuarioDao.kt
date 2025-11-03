package com.example.ejemploroom.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ejemploroom.model.Usuario

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insertar(usuario: Usuario)

    @Query("SELECT * FROM Usuario")
    suspend fun obtenerUsuarios(): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE nombre = :nombre AND contrasena = :contrasena")
    suspend fun validarUsuario(nombre: String, contrasena: String): Usuario?

    @Query("SELECT * FROM Usuario WHERE nombre = :nombre")
    suspend fun obtenerUsuarioPorNombre(nombre: String): Usuario?
}