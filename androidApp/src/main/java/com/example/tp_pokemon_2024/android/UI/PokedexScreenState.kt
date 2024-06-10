package com.example.tp_pokemon_2024.android.UI

sealed class PokedexScreenState {
    object Loading : PokedexScreenState()

    object Error : PokedexScreenState()

    class ShowPokedex(val pokedex: com.example.tp_pokemon_2024.data.Pokedex) : PokedexScreenState()
}