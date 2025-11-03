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

    private val _navegacion = MutableStateFlow<String>("inicio")
    val navegacion: StateFlow<String> = _navegacion.asStateFlow()

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
            cargarUsuarios()
            irAInicio()
        }
    }

    fun iniciarSesion(nombre: String, contrasena: String) {
        viewModelScope.launch {
            val usuario = usuarioDao.validarUsuario(nombre, contrasena)
            if (usuario != null) {
                _usuarioActual.value = usuario
                irACatalogo()
            }
        }
    }

    fun irARegistro() {
        _navegacion.value = "registro"
    }

    fun irALogin() {
        _navegacion.value = "login"
    }

    fun irAInicio() {
        _navegacion.value = "inicio"
    }

    fun irACatalogo() {
        _navegacion.value = "catalogo"
    }

    fun irAPerfil() {
        _navegacion.value = "perfil"
    }

    fun cerrarSesion() {
        _usuarioActual.value = null
        irAInicio()
    }
}