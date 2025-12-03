package com.example.ejemploroom.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemploroom.viewmodel.ProductoViewModel

@Composable
fun EditarProductosScreen(
    onVolverCatalogo: () -> Unit,
    productoViewModel: ProductoViewModel
) {
    val modoEdicion by productoViewModel.modoEdicion.collectAsState()
    val productos by productoViewModel.productos.collectAsState()
    val productoEditando by productoViewModel.productoEditando.collectAsState()
    val mensajeGlobal by productoViewModel.mensaje.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Administración de Productos",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = onVolverCatalogo
            ) {
                Text("Volver al Catálogo")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar mensaje global si existe
        if (mensajeGlobal.isNotEmpty()) {
            Text(
                text = mensajeGlobal,
                color = if (mensajeGlobal.contains("✓")) Color.Green else Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }

        when (modoEdicion) {
            "menu" -> MenuPrincipal(productoViewModel)
            "agregar" -> FormularioAgregar(productoViewModel)
            "editar" -> {
                if (productoEditando != null) {
                    FormularioEditar(productoViewModel, productoEditando!!)
                } else {
                    BuscarProductoParaEditar(productoViewModel)
                }
            }
            "eliminar" -> FormularioEliminar(productoViewModel)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de productos (solo en modo menú)
        if (modoEdicion == "menu" && productos.isNotEmpty()) {
            Text(
                text = "Productos existentes (${productos.size})",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(productos) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "ID: ${producto.id}",
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "Nombre: ${producto.nombre}")
                            Text(text = "Precio: ${producto.precio}")
                            Text(text = "Stock: ${producto.stock}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MenuPrincipal(productoViewModel: ProductoViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "¿Qué deseas hacer?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = { productoViewModel.cambiarModoEdicion("agregar") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Agregar Nuevo Producto", fontSize = 16.sp)
        }

        Button(
            onClick = { productoViewModel.cambiarModoEdicion("editar") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Editar Producto Existente", fontSize = 16.sp)
        }

        Button(
            onClick = { productoViewModel.cambiarModoEdicion("eliminar") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Eliminar Producto", fontSize = 16.sp)
        }
    }
}

@Composable
fun FormularioAgregar(productoViewModel: ProductoViewModel) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Agregar Nuevo Producto",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio (ej: \$59.990)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = stock,
            onValueChange = { stock = it },
            label = { Text("Stock disponible (número)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = imagenUrl,
            onValueChange = { imagenUrl = it },
            label = { Text("URL de la imagen (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    productoViewModel.cambiarModoEdicion("menu")
                    nombre = ""
                    precio = ""
                    stock = ""
                    imagenUrl = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = {
                    if (nombre.isNotBlank() && precio.isNotBlank() && stock.isNotBlank()) {
                        val stockInt = stock.toIntOrNull()
                        if (stockInt != null) {
                            productoViewModel.agregarProducto(nombre, precio, stockInt, imagenUrl)
                            nombre = ""
                            precio = ""
                            stock = ""
                            imagenUrl = ""
                        } else {
                            productoViewModel.setMensaje("Por favor ingresa un número válido para el stock")
                        }
                    } else {
                        productoViewModel.setMensaje("Por favor completa todos los campos obligatorios")
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Guardar Producto")
            }
        }
    }
}

@Composable
fun BuscarProductoParaEditar(productoViewModel: ProductoViewModel) {
    var idInput by remember { mutableStateOf("") }
    var buscarProducto by remember { mutableStateOf(false) }

    // Efecto para buscar producto cuando se activa la bandera
    val id = idInput.toIntOrNull()

    if (buscarProducto && id != null) {
        LaunchedEffect(id) {
            productoViewModel.buscarProductoParaEditar(id)
            buscarProducto = false
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Editar Producto",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text("Ingresa el ID del producto que deseas editar:")

        OutlinedTextField(
            value = idInput,
            onValueChange = {
                idInput = it
                productoViewModel.setMensaje("")
            },
            label = { Text("ID del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    productoViewModel.cambiarModoEdicion("menu")
                    idInput = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = {
                    val id = idInput.toIntOrNull()
                    if (id != null) {
                        buscarProducto = true
                    } else {
                        productoViewModel.setMensaje("Por favor ingresa un ID válido (número)")
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Buscar Producto")
            }
        }
    }
}

@Composable
fun FormularioEditar(
    productoViewModel: ProductoViewModel,
    producto: com.example.ejemploroom.model.Producto
) {
    var nombre by remember { mutableStateOf(producto.nombre) }
    var precio by remember { mutableStateOf(producto.precio) }
    var stock by remember { mutableStateOf(producto.stock.toString()) } // Convertir Int a String para TextField
    var imagenUrl by remember { mutableStateOf(producto.imagenUrl) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Editando Producto ID: ${producto.id}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = stock,
            onValueChange = { stock = it },
            label = { Text("Stock disponible (número)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = imagenUrl,
            onValueChange = { imagenUrl = it },
            label = { Text("URL de la imagen") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    productoViewModel.setProductoEditando(null)
                    productoViewModel.cambiarModoEdicion("menu")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = {
                    if (nombre.isNotBlank() && precio.isNotBlank() && stock.isNotBlank()) {
                        val stockInt = stock.toIntOrNull()
                        if (stockInt != null) {
                            productoViewModel.actualizarProducto(
                                producto.id,
                                nombre,
                                precio,
                                stockInt,
                                imagenUrl
                            )
                        } else {
                            productoViewModel.setMensaje("Por favor ingresa un número válido para el stock")
                        }
                    } else {
                        productoViewModel.setMensaje("Por favor completa todos los campos obligatorios")
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Guardar Cambios")
            }
        }
    }
}

@Composable
fun FormularioEliminar(productoViewModel: ProductoViewModel) {
    var idInput by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Eliminar Producto",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text("Ingresa el ID del producto que deseas eliminar:")

        OutlinedTextField(
            value = idInput,
            onValueChange = {
                idInput = it
                productoViewModel.setMensaje("")
            },
            label = { Text("ID del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    productoViewModel.cambiarModoEdicion("menu")
                    idInput = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = {
                    val id = idInput.toIntOrNull()
                    if (id != null) {
                        productoViewModel.eliminarProductoPorId(id)
                        idInput = ""
                    } else {
                        productoViewModel.setMensaje("Por favor ingresa un ID válido (número)")
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Eliminar Producto")
            }
        }
    }
}