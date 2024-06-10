package com.example.tp_pokemon_2024

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun initLogger()