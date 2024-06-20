package com.example.tp_pokemon_2024.data.repository

import com.example.tp_pokemon_2024.data.Pokedex
class CommonPokedexRepository(
    private val apiRepository: ApiPokedexRepository,
    private val dbRepository: PokedexDBrepository
) {

    suspend fun getPokedex(): Pokedex {
        return try {
            // Obtener datos desde la API
            val pokedex = apiRepository.getPokedex()
            // Guardar en la base de datos local
            dbRepository.addAllPokemon(pokedex.results)
            pokedex
        } catch (e: Exception) {
            // Si hay un error al obtener desde la API, cargar desde la base de datos
            loadFromDatabase()
        }
    }

    private suspend fun loadFromDatabase(): Pokedex {
        val pokemonList = dbRepository.getAllPokemon()
        return if (pokemonList.isNotEmpty()) {
            Pokedex(results = pokemonList)
        } else {
            throw IllegalStateException("No data available in database")
        }
    }

}
