package com.example.ejemploroom.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)  // ← Opcional, pero ayuda
data class PokemonResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "sprites") val sprites: Sprites
)

@JsonClass(generateAdapter = true)  // ← Opcional
data class Sprites(
    @Json(name = "front_default") val frontDefault: String
)