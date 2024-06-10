package com.example.tp_pokemon_2024.android.Data


import com.example.tp_pokemon_2024.data.Pokedex
import retrofit2.Response
import retrofit2.http.GET

interface PokedexClient {

    @GET("pokemon/?limit=800")
    suspend fun get() : Response<Pokedex>

}