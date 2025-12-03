package com.example.ejemploroom.ui.screen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)  // Usar AndroidJUnit4 en lugar de JUnit5
class PantallaInicioTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tituloPrincipalEsVisible() {
        // Prueba simple que verifica el título
        composeTestRule.setContent {
            PantallaInicio(
                onRegistroClick = {},
                onLoginClick = {},
                pokeViewModel = null
            )
        }

        // Solo verifica que el título existe
        composeTestRule.onNodeWithText("LEVEL UP STORE", substring = true).assertExists()
    }

    @Test
    fun botonesSonVisibles() {
        // Prueba simple que verifica los botones
        composeTestRule.setContent {
            PantallaInicio(
                onRegistroClick = {},
                onLoginClick = {},
                pokeViewModel = null
            )
        }

        // Verifica ambos botones
        composeTestRule.onNodeWithText("Registrarse", substring = true).assertExists()
        composeTestRule.onNodeWithText("Iniciar Sesión", substring = true).assertExists()
    }
}