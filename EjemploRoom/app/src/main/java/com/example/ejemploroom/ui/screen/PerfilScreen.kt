package com.example.ejemploroom.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemploroom.viewmodel.FormularioViewModel

@Composable
fun PerfilScreen(
    onVolverCatalogo: () -> Unit,
    onCerrarSesion: () -> Unit,
    viewModel: FormularioViewModel
) {
    val usuarioActual by viewModel.usuarioActual.collectAsState()

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
                text = "Mi Perfil",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = onVolverCatalogo
            ) {
                Text("Volver al Catálogo")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // SOLUCIÓN: Usar usuarioActual?.let {} en lugar de if directo
        usuarioActual?.let { usuario ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Información del Usuario",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    InfoRow("Nombre:", usuario.nombre)
                    InfoRow("RUT:", usuario.rut)
                    InfoRow("Correo:", usuario.correo)
                    InfoRow("Edad:", usuario.edad)

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            viewModel.cerrarSesion()
                            onCerrarSesion()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cerrar Sesión")
                    }
                }
            }
        } ?: run {
            // Caso cuando usuarioActual es null
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No hay usuario logueado")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onCerrarSesion) {
                    Text("Volver al Inicio")
                }
            }
        }
    }
}

@Composable
fun InfoRow(etiqueta: String, valor: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = etiqueta,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = valor,
            modifier = Modifier.weight(1f)
        )
    }
}