package com.example.tp_pokemon_2024.android.UI

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.tp_pokemon_2024.android.Data.Pokedex

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.example.tp_pokemon_2024.data.Pokedex
import com.example.tp_pokemon_2024.data.PokedexResults
import com.example.tp_pokemon_2024.data.repository.ApiPokedexRepository
import com.example.tp_pokemon_2024.data.repository.PokedexDBrepository


/*class PokedexViewModel(
    private val pokedexRepository: CommonPokedexRepository,
    private val dbRepository: PokedexDBrepository
) : ViewModel() {


    val pokedex = MutableLiveData<Pokedex>()

    private val _screenState: MutableStateFlow<PokedexScreenState> = MutableStateFlow(
        PokedexScreenState.Loading)
    val screenState: Flow<PokedexScreenState> = _screenState

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("PokedexViewModel", "Error retrieving pokedex: ${throwable.message}")
        }

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            kotlin.runCatching {
                val pokedexData = pokedexRepository.get() // Obtener datos de la API
                dbRepository.addAllPokemon(pokedexData.toPokedexResultsList()) // Guardar en la base de datos
                pokedex.postValue(pokedexData)
                _screenState.value = PokedexScreenState.ShowPokedex(pokedexData)
            }.onFailure { exception ->
                Log.d("PokedexViewModel", "Error retrieving pokedex: ${exception.message}")
                _screenState.value = PokedexScreenState.Error
            }
        }
    }
}*/
class PokedexViewModel(
    private val apiRepository: ApiPokedexRepository,
    private val dbRepository: PokedexDBrepository
) : ViewModel() {

    val pokedex = MutableLiveData<Pokedex>()

    private val _screenState: MutableStateFlow<PokedexScreenState> = MutableStateFlow(
        PokedexScreenState.Loading
    )
    val screenState: Flow<PokedexScreenState> = _screenState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("PokedexViewModel", "Error retrieving pokedex: ${throwable.message}")
        loadFromDatabase()
    }

    init {
        fetchPokedex()
    }

    private fun fetchPokedex() {
        viewModelScope.launch(coroutineExceptionHandler) {
            kotlin.runCatching {
                apiRepository.getPokedex()
            }.onSuccess { pokedexData ->
                // Guardar los datos en la base de datos local
                saveToDatabase(pokedexData.results)

                // Actualizar el estado de la pantalla con los datos obtenidos
                updateScreenState(pokedexData.results)
            }.onFailure {
                Log.d("PokedexViewModel", "Error retrieving pokedex: ${it.message}")
                loadFromDatabase()
            }
        }
    }

    private fun saveToDatabase(pokemons: List<PokedexResults>) {
        viewModelScope.launch {
            dbRepository.addAllPokemon(pokemons)
        }
    }

    fun loadFromDatabase() {
        viewModelScope.launch {
            try {
                val cachedPokedex = dbRepository.getAllPokemon()
                if (cachedPokedex.isNotEmpty()) {
                    updateScreenState(cachedPokedex)
                } else {
                    _screenState.value = PokedexScreenState.Error
                }
            } catch (e: Exception) {
                Log.e("PokedexViewModel", "Error loading from database: ${e.message}")
                _screenState.value = PokedexScreenState.Error
            }
        }
    }

    private fun updateScreenState(pokemonList: List<PokedexResults>) {
        val updatedPokedex = Pokedex(
            previous = null, // Aquí puedes asignar el valor que corresponda a `previous`
            results = pokemonList
        )
        pokedex.postValue(updatedPokedex)
        _screenState.value = PokedexScreenState.ShowPokedex(updatedPokedex)
    }

}
