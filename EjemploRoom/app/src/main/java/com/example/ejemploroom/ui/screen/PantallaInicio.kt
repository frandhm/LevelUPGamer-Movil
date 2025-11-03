package com.example.ejemploroom.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemploroom.viewmodel.FormularioViewModel

@Composable
fun PantallaInicio(viewModel: FormularioViewModel) {
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
            onClick = { viewModel.irARegistro() },
            modifier = Modifier
                .fillMaxWidth()
                .size(55.dp)
        ) {
            Text("Registrarse", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.irALogin() },
            modifier = Modifier
                .fillMaxWidth()
                .size(55.dp)
        ) {
            Text("Iniciar Sesión", fontSize = 16.sp)
        }
    }
}