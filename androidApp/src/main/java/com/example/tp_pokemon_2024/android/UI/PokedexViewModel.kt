package com.example.tp_pokemon_2024.android.UI

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.tp_pokemon_2024.android.Data.Pokedex
import com.example.tp_pokemon_2024.data.repository.CommonPokedexRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.example.tp_pokemon_2024.data.Pokedex


class PokedexViewModel(private val pokedexRepository: CommonPokedexRepository) : ViewModel() {

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
                pokedexRepository.get()
            }.onSuccess { pokedexData ->
                pokedex.postValue(pokedexData)
                _screenState.value = PokedexScreenState.ShowPokedex(pokedexData)
            }.onFailure { exception ->
                Log.d("PokedexViewModel", "Error retrieving pokedex: ${exception.message}")
                _screenState.value = PokedexScreenState.Error
            }
        }
    }
}
