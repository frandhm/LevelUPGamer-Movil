package com.example.ejemploroom.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ejemploroom.viewmodel.FormularioViewModel

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: String,
    val imagenUrl: String
)

@Composable
fun CatalogoScreen(viewModel: FormularioViewModel) {
    val usuarioActual = viewModel.usuarioActual.collectAsState().value

    // Lista de productos con placeholder en lugar de URLs PREGUNTAR AL PROFEXD
    val productos = listOf(
        Producto(
            id = 1,
            nombre = "The Legend of Zelda: Tears of the Kingdom",
            precio = "$59.990",
            imagenUrl = ""
        ),
        Producto(
            id = 2,
            nombre = "God of War Ragnarök",
            precio = "$54.990",
            imagenUrl = ""
        ),
        Producto(
            id = 3,
            nombre = "Elden Ring",
            precio = "$49.990",
            imagenUrl = ""
        ),
        Producto(
            id = 4,
            nombre = "Cyberpunk 2077",
            precio = "$39.990",
            imagenUrl = ""
        ),
        Producto(
            id = 5,
            nombre = "Minecraft",
            precio = "$24.990",
            imagenUrl = ""
        ),
        Producto(
            id = 6,
            nombre = "Super Mario Odyssey",
            precio = "$44.990",
            imagenUrl = ""
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

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
                onClick = { viewModel.irAPerfil() }
            ) {
                Text(text = "Perfil: ${usuarioActual?.nombre ?: "Usuario"}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de productos
        LazyColumn {
            items(productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Placeholder temporal en lugar de imagen
                        Text(
                            text = "🕹️ IMAGEN DEL JUEGO",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )

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
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}