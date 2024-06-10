package com.example.tp_pokemon_2024.android.Data

import com.google.gson.annotations.SerializedName

data class PokedexResults(
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "url")
    val url: String
)
