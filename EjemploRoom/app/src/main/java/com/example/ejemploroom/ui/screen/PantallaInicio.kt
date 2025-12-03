package com.example.ejemploroom.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ejemploroom.viewmodel.PokeViewModel


@Composable
fun PantallaInicio(
    onRegistroClick: () -> Unit,
    onLoginClick: () -> Unit,
    pokeViewModel: PokeViewModel? = null  // Opcional para no romper navegación
) {
    val pokemonDestacado by pokeViewModel?.pokemonDestacado?.collectAsState()
        ?: kotlin.run { androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(null) } }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // CONTENIDO ORIGINAL (SIN CAMBIOS)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "LEVEL UP STORE",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tu tienda de videojuegos",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onRegistroClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text("Registrarse", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text("Iniciar Sesión", fontSize = 16.sp)
            }
        }

        // POKEMON DESTACADO
        if (pokemonDestacado != null) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pokemon destacado: ",
                    fontSize = 24.sp
                )

                AsyncImage(
                    model = pokemonDestacado?.imagenUrl,
                    contentDescription = pokemonDestacado?.nombre,
                    modifier = Modifier.size(40.dp)
                )

                Text(
                    text = pokemonDestacado?.nombre ?: "",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}