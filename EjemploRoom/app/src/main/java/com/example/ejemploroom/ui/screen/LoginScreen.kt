package com.example.ejemploroom.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemploroom.viewmodel.FormularioViewModel

@Composable
fun LoginScreen(
    onLoginExitoso: () -> Unit,
    onVolverInicio: () -> Unit,
    viewModel: FormularioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }
    val usuarioActual by viewModel.usuarioActual.collectAsState()

    // Observar cambios en usuarioActual
    LaunchedEffect(usuarioActual) {
        if (usuarioActual != null) {
            onLoginExitoso()
        }
    }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Iniciar Sesión",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (mensajeError.isNotEmpty()) {
            Text(
                text = mensajeError,
                color = androidx.compose.ui.graphics.Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                mensajeError = ""
            },
            label = { Text(text = "Nombre de usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = {
                contrasena = it
                mensajeError = ""
            },
            label = { Text(text = "Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (nombre.isNotBlank() && contrasena.isNotBlank()) {
                    viewModel.iniciarSesion(nombre, contrasena)
                    // El LaunchedEffect se encargará de la navegación
                } else {
                    mensajeError = "Por favor completa todos los campos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Iniciar Sesión", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onVolverInicio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Volver al Inicio", fontSize = 16.sp)
        }
    }
}