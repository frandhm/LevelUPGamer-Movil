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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ejemploroom.viewmodel.FormularioViewModel
import androidx.compose.ui.unit.sp

@Composable
fun RegistroScreen(viewModel: FormularioViewModel) {
    var nombre by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

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

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(text = "Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = rut,
            onValueChange = { rut = it },
            label = { Text(text = "RUT") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text(text = "Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text(text = "Edad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text(text = "Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nombre.isNotBlank() && rut.isNotBlank() && correo.isNotBlank() &&
                    edad.isNotBlank() && contrasena.isNotBlank()) {
                    viewModel.agregarUsuario(nombre, rut, correo, edad, contrasena)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.irAInicio() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Volver al Inicio", fontSize = 16.sp)
        }
    }
}