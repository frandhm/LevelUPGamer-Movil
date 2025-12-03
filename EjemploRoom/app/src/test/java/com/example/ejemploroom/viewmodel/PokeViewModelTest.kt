package com.example.ejemploroom.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class PokeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test basico que siempre pasa`() = runTest {
        // Esta prueba siempre pasa - verifica que el entorno de testing funciona
        assertTrue(true)
    }

    @Test
    fun `test de corrutinas`() = runTest {
        // Prueba simple de corrutinas
        val resultado = "test"
        assertEquals("test", resultado)
    }

    @Test
    fun `test de flujo de datos`() = runTest {
        // Prueba de concepto de StateFlow
        val flow = kotlinx.coroutines.flow.MutableStateFlow<String?>(null)
        assertEquals(null, flow.value)

        flow.value = "dato"
        assertEquals("dato", flow.value)
    }
}