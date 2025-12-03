package com.example.ejemploroom.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ejemploroom.R
import com.example.ejemploroom.viewmodel.FormularioViewModel
import com.example.ejemploroom.viewmodel.ProductoViewModel

@Composable
fun CatalogoScreen(
    onPerfilClick: () -> Unit,
    onEditarProductosClick: () -> Unit,
    viewModel: FormularioViewModel,
    productoViewModel: ProductoViewModel
) {
    val usuarioActual by viewModel.usuarioActual.collectAsState()
    val productos by productoViewModel.productos.collectAsState()

    // Validación simple: Solo mostrar botón si el usuario es "admin"
    val esAdmin = usuarioActual?.nombre?.lowercase() == "admin"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header con botón de perfil
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Catálogo Level Up Store",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Button(
                    onClick = onPerfilClick
                ) {
                    Text(text = "Perfil: ${usuarioActual?.nombre ?: "Usuario"}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de productos desde la base de datos
            if (productos.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("No hay productos disponibles")
                    // Solo mostrar este mensaje adicional si el usuario es admin
                    if (esAdmin) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Usa el botón abajo para agregar productos")
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(productos) { producto ->
                        ProductoCard(producto = producto)
                    }
                }
            }
        }

        // Botón "Editar Productos" en el centro inferior (solo para admin)
        if (esAdmin) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = onEditarProductosClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        text = "Editar Productos",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ProductoCard(producto: com.example.ejemploroom.model.Producto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Imagen con placeholder en caso de error
            if (producto.imagenUrl.isNotEmpty()) {
                AsyncImage(
                    model = producto.imagenUrl,
                    contentDescription = "Imagen de ${producto.nombre}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    error = painterResource(id = R.drawable.ic_launcher_foreground)
                )
            } else {
                // Placeholder si no hay URL
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Imagen no disponible",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = producto.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Precio: ${producto.precio}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2E7D32)
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Stock: ${producto.stock}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}