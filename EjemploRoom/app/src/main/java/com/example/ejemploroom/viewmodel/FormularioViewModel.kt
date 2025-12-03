package com.example.ejemploroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejemploroom.data.UsuarioDao
import com.example.ejemploroom.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FormularioViewModel(private val usuarioDao: UsuarioDao) : ViewModel() {
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> = _usuarios.asStateFlow()

    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual: StateFlow<Usuario?> = _usuarioActual.asStateFlow()

    init {
        cargarUsuarios()
    }

    private fun cargarUsuarios() {
        viewModelScope.launch {
            _usuarios.value = usuarioDao.obtenerUsuarios()
        }
    }

    fun agregarUsuario(nombre: String, rut: String, correo: String, edad: String, contrasena: String) {
        viewModelScope.launch {
            val nuevoUsuario = Usuario(
                nombre = nombre,
                rut = rut,
                correo = correo,
                edad = edad,
                contrasena = contrasena
            )
            usuarioDao.insertar(nuevoUsuario)
            cargarUsuarios() // Esto asegura que la lista se actualice
        }
    }

    fun iniciarSesion(nombre: String, contrasena: String) {
        viewModelScope.launch {
            val usuario = usuarioDao.validarUsuario(nombre, contrasena)
            _usuarioActual.value = usuario
        }
    }

    fun cerrarSesion() {
        _usuarioActual.value = null
    }
}