package com.example.tp_pokemon_2024.android.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp_pokemon_2024.data.repository.CommonPokedexRepository

/*
class PokedexViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pokedexClient = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokedexClient::class.java)

        val pokedexRepository = PokedexRepositoryImp(pokedexClient)

        return PokedexViewModel(pokedexRepository) as T
    }
}*/
class PokedexViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pokedexRepository = CommonPokedexRepository()

        return PokedexViewModel(pokedexRepository) as T
    }
}