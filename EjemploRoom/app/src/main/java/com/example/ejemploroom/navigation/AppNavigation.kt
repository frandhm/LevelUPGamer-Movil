package com.example.ejemploroom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejemploroom.ui.screen.CatalogoScreen
import com.example.ejemploroom.ui.screen.LoginScreen
import com.example.ejemploroom.ui.screen.PantallaInicio
import com.example.ejemploroom.ui.screen.PerfilScreen
import com.example.ejemploroom.ui.screen.RegistroScreen
import com.example.ejemploroom.ui.screen.EditarProductosScreen
import com.example.ejemploroom.viewmodel.FormularioViewModel
import com.example.ejemploroom.viewmodel.ProductoViewModel
import com.example.ejemploroom.viewmodel.PokeViewModel

@Composable
fun AppNavigation(
    viewModel: FormularioViewModel,
    productoViewModel: ProductoViewModel,
    pokeViewModel: PokeViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") {
            PantallaInicio(
                onRegistroClick = { navController.navigate("registro") },
                onLoginClick = { navController.navigate("login") },
                pokeViewModel = pokeViewModel  // Pasar aquí
            )
        }
        composable("registro") {
            RegistroScreen(
                onRegistroCompletado = {
                    navController.popBackStack()
                    navController.navigate("inicio")
                },
                onVolverInicio = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
        composable("login") {
            LoginScreen(
                onLoginExitoso = { navController.navigate("catalogo") },
                onVolverInicio = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
        composable("catalogo") {
            CatalogoScreen(
                onPerfilClick = { navController.navigate("perfil") },
                onEditarProductosClick = { navController.navigate("editar_productos") },
                viewModel = viewModel,
                productoViewModel = productoViewModel
            )
        }
        composable("perfil") {
            PerfilScreen(
                onVolverCatalogo = { navController.popBackStack() },
                onCerrarSesion = {
                    navController.popBackStack("inicio", inclusive = false)
                },
                viewModel = viewModel
            )
        }
        composable("editar_productos") {
            EditarProductosScreen(
                onVolverCatalogo = { navController.popBackStack() },
                productoViewModel = productoViewModel
            )
        }
    }
}