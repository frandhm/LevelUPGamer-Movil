package com.example.ejemploroom.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PokeRepository {
    // Crear Moshi con KotlinJsonAdapterFactory
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())  // ← IMPORTANTE: Agregar esto
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))  // ← Pasar moshi aquí
        .build()

    private val service = retrofit.create(PokeApiService::class.java)

    suspend fun getPokemonAleatorio(): PokemonDestacado {
        // Pokemon ID aleatorio entre 1 y 151 (primera generación)
        val randomId = (1..151).random()
        val response = service.getPokemon(randomId)

        return PokemonDestacado(
            nombre = response.name.replaceFirstChar { it.uppercase() },
            imagenUrl = response.sprites.frontDefault
        )
    }
}