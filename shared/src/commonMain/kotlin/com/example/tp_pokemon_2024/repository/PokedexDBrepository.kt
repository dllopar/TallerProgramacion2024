package com.example.tp_pokemon_2024.repository

import com.example.pokedex.Database
import com.example.tp_pokemon_2024.DatabaseDriverFactory
import com.example.tp_pokemon_2024.data.PokedexResults

class PokedexDBrepository(databaseDriverFactory: DatabaseDriverFactory){

    private val database = Database(databaseDriverFactory.createDriver())
    private val dbQuery = database.pokemonQueries

    fun getAllPokemon(): List<PokedexResults>{
        return dbQuery.getAllPokemon(::mapPokemonSelecting).executeAsList()
    }

    fun addAllPokemon(pokemonList: List<PokedexResults>) {
        database.transaction {
            pokemonList.forEach { pokemon ->
                addPokemon(pokemon)
            }
        }
    }

    private fun mapPokemonSelecting(
        mapName: String,
        mapUrl: String
    ):PokedexResults{
        return PokedexResults(name = mapName, url = mapUrl)
    }

    fun addPokemon(pokemon: PokedexResults) {
        val existingPokemon = dbQuery.getPokemon(pokemon.name).executeAsOneOrNull()

        if (existingPokemon == null) {
            dbQuery.insertPokemon(name = pokemon.name, url = pokemon.url)
        }
    }

}