package com.example.tp_pokemon_2024.android.Data


import com.example.tp_pokemon_2024.android.Data.PokedexClient
import com.example.tp_pokemon_2024.android.Domain.PokedexRepository
import com.example.tp_pokemon_2024.data.Pokedex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PokedexRepositoryImp(private val pokedexClient: PokedexClient) : PokedexRepository {

    override suspend fun getPokedex(): Response<Pokedex> {
        return withContext(Dispatchers.IO) {
            pokedexClient.get()
        }
    }
}