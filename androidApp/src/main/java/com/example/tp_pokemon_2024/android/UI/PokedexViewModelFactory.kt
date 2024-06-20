package com.example.tp_pokemon_2024.android.UI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp_pokemon_2024.data.repository.ApiPokedexRepository
import com.example.tp_pokemon_2024.data.repository.CommonPokedexRepository
import com.example.tp_pokemon_2024.data.repository.PokedexDBrepository
/*
class PokedexViewModelFactory(
    private val dbRepository: PokedexDBrepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pokedexRepository = CommonPokedexRepository()

        return PokedexViewModel(pokedexRepository, dbRepository) as T
    }
}
class PokedexViewModelFactory(
    private val dbRepository: PokedexDBrepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokedexViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokedexViewModel(dbRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
*/
class PokedexViewModelFactory(
    private val apiRepository: ApiPokedexRepository,
    private val dbRepository: PokedexDBrepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokedexViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokedexViewModel(apiRepository, dbRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
