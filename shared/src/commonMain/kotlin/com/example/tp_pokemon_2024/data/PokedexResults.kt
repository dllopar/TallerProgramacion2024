package com.example.tp_pokemon_2024.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexResults(
    @SerialName(value = "name")
    val name: String,
    @SerialName(value = "url")
    val url: String
)

