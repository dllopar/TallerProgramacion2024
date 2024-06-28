package com.example.tp_pokemon_2024.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(
    @SerialName(value = "previous")
    val previous: String? = null,
    @SerialName(value = "results")
    val results: List<PokedexResults>
)
