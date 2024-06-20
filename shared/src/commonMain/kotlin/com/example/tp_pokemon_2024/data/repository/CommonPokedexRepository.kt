package com.example.tp_pokemon_2024.data.repository

import com.example.pokedex.Database
import com.example.pokedex.PokemonQueries
import com.example.tp_pokemon_2024.DatabaseDriverFactory
import com.example.tp_pokemon_2024.data.Pokedex
import com.example.tp_pokemon_2024.initLogger
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
//import io.ktor.util.logging.Logger
import io.ktor.client.plugins.logging.Logger
import kotlinx.serialization.json.Json

class CommonPokedexRepository {

    private val httpClient = HttpClient {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "HttpClient", message = message)
                }
            }
        }
        install(ContentNegotiation) {

            json(
                Json {

                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }
            )
        }

    }.also {
        initLogger()
    }
    suspend fun get() : Pokedex {
        val result = httpClient.get("https://pokeapi.co/api/v2/pokemon/?limit=800"){
        }.body<Pokedex>()
        return result
    }
}
