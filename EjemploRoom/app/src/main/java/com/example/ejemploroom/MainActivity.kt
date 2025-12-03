package com.example.ejemploroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.example.ejemploroom.data.ProductoDatabase
import com.example.ejemploroom.data.UsuarioDatabase
import com.example.ejemploroom.navigation.AppNavigation
import com.example.ejemploroom.ui.theme.EjemploRoomTheme
import com.example.ejemploroom.viewmodel.FormularioViewModel
import com.example.ejemploroom.viewmodel.PokeViewModel
import com.example.ejemploroom.viewmodel.ProductoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploRoomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormularioApp()
                }
            }
        }
    }
}

@Composable
fun FormularioApp() {
    val context = LocalContext.current

    // 1. BASE DE DATOS DE USUARIOS
    val usuarioDatabase = remember {
        Room.databaseBuilder(
            context,
            UsuarioDatabase::class.java,
            "usuario.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    // 2. BASE DE DATOS DE PRODUCTOS
    val productoDatabase = remember {
        Room.databaseBuilder(
            context,
            ProductoDatabase::class.java,
            "producto.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    // 3. VIEWMODEL DE USUARIOS
    val viewModel = remember {
        FormularioViewModel(usuarioDatabase.usuarioDao())
    }

    // 4. VIEWMODEL DE PRODUCTOS
    val productoViewModel = remember {
        ProductoViewModel(productoDatabase.productoDao())
    }

    // 5. NUEVO VIEWMODEL PARA POKÉMON (API REST)
    val pokeViewModel = remember {
        PokeViewModel()
    }

    // 6. NAVEGACIÓN CON TODOS LOS VIEWMODELS
    AppNavigation(
        viewModel = viewModel,               // Para usuarios (login/registro/perfil)
        productoViewModel = productoViewModel, // Para productos (catálogo/edición)
        pokeViewModel = pokeViewModel         // Para Pokémon destacado (API REST)
    )
}