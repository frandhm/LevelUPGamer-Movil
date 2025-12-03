package com.example.ejemploroom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ejemploroom.model.Producto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {
    @Insert
    suspend fun insertar(producto: Producto): Long // Devuelve el ID generado

    @Update
    suspend fun actualizar(producto: Producto)

    @Delete
    suspend fun eliminar(producto: Producto)

    @Query("SELECT * FROM Producto ORDER BY id DESC")
    fun obtenerTodosProductos(): Flow<List<Producto>>

    @Query("SELECT * FROM Producto WHERE id = :id")
    suspend fun obtenerProductoPorId(id: Int): Producto?

    @Query("DELETE FROM Producto WHERE id = :id")
    suspend fun eliminarPorId(id: Int)

    @Query("SELECT COUNT(*) FROM Producto")
    suspend fun contarProductos(): Int
}