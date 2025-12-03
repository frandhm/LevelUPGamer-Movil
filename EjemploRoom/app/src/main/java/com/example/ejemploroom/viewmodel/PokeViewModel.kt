package com.example.ejemploroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejemploroom.api.PokeRepository
import com.example.ejemploroom.api.PokemonDestacado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokeViewModel(
    private val repository: PokeRepository = PokeRepository()
) : ViewModel() {
    private val _pokemonDestacado = MutableStateFlow<PokemonDestacado?>(null)
    val pokemonDestacado: StateFlow<PokemonDestacado?> = _pokemonDestacado.asStateFlow()

    init {
        cargarPokemonAleatorio()
    }

    private fun cargarPokemonAleatorio() {
        viewModelScope.launch {
            try {
                _pokemonDestacado.value = repository.getPokemonAleatorio()
            } catch (e: Exception) {
                e.printStackTrace()
                _pokemonDestacado.value = null
            }
        }
    }
}