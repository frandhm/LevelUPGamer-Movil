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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemploroom.viewmodel.FormularioViewModel

@Composable
fun RegistroScreen(
    onRegistroCompletado: () -> Unit,
    onVolverInicio: () -> Unit,
    viewModel: FormularioViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var esExito by remember { mutableStateOf(false) }

    // Observar la lista de usuarios para detectar cuando se agrega uno nuevo
    val usuarios by viewModel.usuarios.collectAsState()

    // Navegar automáticamente cuando el registro es exitoso
    LaunchedEffect(esExito) {
        if (esExito) {
            // Pequeña espera para que el usuario vea el mensaje de éxito
            kotlinx.coroutines.delay(1500)
            onRegistroCompletado()
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
            text = "Registro - Level Up Store",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar mensaje
        if (mensaje.isNotEmpty()) {
            Text(
                text = mensaje,
                color = if (esExito) Color.Green else Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                mensaje = ""
            },
            label = { Text(text = "Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = rut,
            onValueChange = {
                rut = it
                mensaje = ""
            },
            label = { Text(text = "RUT") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = {
                correo = it
                mensaje = ""
            },
            label = { Text(text = "Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = {
                edad = it
                mensaje = ""
            },
            label = { Text(text = "Edad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = {
                contrasena = it
                mensaje = ""
            },
            label = { Text(text = "Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nombre.isNotBlank() && rut.isNotBlank() && correo.isNotBlank() &&
                    edad.isNotBlank() && contrasena.isNotBlank()) {

                    viewModel.agregarUsuario(nombre, rut, correo, edad, contrasena)
                    mensaje = "¡Usuario registrado exitosamente! Redirigiendo..."
                    esExito = true

                    // Limpiar campos después del registro
                    nombre = ""
                    rut = ""
                    correo = ""
                    edad = ""
                    contrasena = ""

                } else {
                    mensaje = "Por favor completa todos los campos"
                    esExito = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrarse")
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