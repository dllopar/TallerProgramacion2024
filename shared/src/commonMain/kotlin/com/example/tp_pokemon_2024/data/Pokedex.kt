package com.example.tp_pokemon_2024.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(
    @SerialName(value = "count")
    val count: Int,
    @SerialName(value = "next")
    val next: String,
    @SerialName(value = "previous")
    val previous: String? = null,
    @SerialName(value = "results")
    val results: List<PokedexResults>
)

