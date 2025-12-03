package com.example.ejemploroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejemploroom.data.ProductoDao
import com.example.ejemploroom.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductoViewModel(private val productoDao: ProductoDao) : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    private val _modoEdicion = MutableStateFlow<String>("menu") // menu, agregar, editar, eliminar
    val modoEdicion: StateFlow<String> = _modoEdicion.asStateFlow()

    private val _productoEditando = MutableStateFlow<Producto?>(null)
    val productoEditando: StateFlow<Producto?> = _productoEditando.asStateFlow()

    private val _mensaje = MutableStateFlow<String>("")
    val mensaje: StateFlow<String> = _mensaje.asStateFlow()

    init {
        cargarProductos()
    }

    private fun cargarProductos() {
        viewModelScope.launch {
            productoDao.obtenerTodosProductos().collect { productos ->
                _productos.value = productos
            }
        }
    }

    fun cambiarModoEdicion(modo: String) {
        _modoEdicion.value = modo
        _mensaje.value = ""
        if (modo != "editar") {
            _productoEditando.value = null
        }
    }

    fun buscarProductoParaEditar(id: Int) {
        viewModelScope.launch {
            val producto = productoDao.obtenerProductoPorId(id)
            if (producto != null) {
                _productoEditando.value = producto
            } else {
                _mensaje.value = "No se encontró un producto con ID: $id"
            }
        }
    }

    fun setProductoEditando(producto: Producto?) {
        _productoEditando.value = producto
    }

    fun setMensaje(mensaje: String) {
        _mensaje.value = mensaje
    }

    fun agregarProducto(nombre: String, precio: String, stock: Int, imagenUrl: String) {
        viewModelScope.launch {
            val nuevoProducto = Producto(
                nombre = nombre,
                precio = precio,
                stock = stock,
                imagenUrl = imagenUrl
            )
            productoDao.insertar(nuevoProducto)
            _mensaje.value = "✓ Producto agregado con éxito"
        }
    }

    fun actualizarProducto(id: Int, nombre: String, precio: String, stock: Int, imagenUrl: String) {
        viewModelScope.launch {
            val productoActualizado = Producto(
                id = id,
                nombre = nombre,
                precio = precio,
                stock = stock,
                imagenUrl = imagenUrl
            )
            productoDao.actualizar(productoActualizado)
            _mensaje.value = "✓ Producto actualizado con éxito"
        }
    }

    fun eliminarProductoPorId(id: Int) {
        viewModelScope.launch {
            productoDao.eliminarPorId(id)
            _mensaje.value = "✓ Producto con ID $id eliminado con éxito"
        }
    }
}