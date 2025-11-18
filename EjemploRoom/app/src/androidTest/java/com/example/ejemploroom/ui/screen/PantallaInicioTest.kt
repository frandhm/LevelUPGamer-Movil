package com.example.ejemploroom.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test


class CatalogoScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun CatalogoScreen_muestraTitulo(){
        composeTestRule.setContent {
            CatalogoScreen())
        }

        composeTestRule.onNodeWithText("Catálogo").assertIsDisplayed()

    }

}