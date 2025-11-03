package com.example.ejemploroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.ejemploroom.data.UsuarioDatabase
import com.example.ejemploroom.ui.screen.CatalogoScreen
import com.example.ejemploroom.ui.screen.LoginScreen
import com.example.ejemploroom.ui.screen.PantallaInicio
import com.example.ejemploroom.ui.screen.PerfilScreen
import com.example.ejemploroom.ui.screen.RegistroScreen
import com.example.ejemploroom.ui.theme.EjemploRoomTheme
import com.example.ejemploroom.viewmodel.FormularioViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormularioApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //RegistroScreen()
}

@Composable
fun FormularioApp() {
    val context = LocalContext.current

    val database = remember {
        Room.databaseBuilder(
            context,
            UsuarioDatabase::class.java,
            "usuario.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    val viewModel = remember {
        FormularioViewModel(database.usuarioDao())
    }

    val navegacion by viewModel.navegacion.collectAsState()

    when (navegacion) {
        "inicio" -> PantallaInicio(viewModel = viewModel)
        "registro" -> RegistroScreen(viewModel = viewModel)
        "login" -> LoginScreen(viewModel = viewModel)
        "catalogo" -> CatalogoScreen(viewModel = viewModel)
        "perfil" -> PerfilScreen(viewModel = viewModel)
        else -> PantallaInicio(viewModel = viewModel)
    }
}

