package com.example.tp_pokemon_2024.android.UI

import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp_pokemon_2024.DatabaseDriverFactory

import com.example.tp_pokemon_2024.android.databinding.ActivityMainBinding
import com.example.tp_pokemon_2024.data.Pokedex
import com.example.tp_pokemon_2024.data.repository.ApiPokedexRepository
import com.example.tp_pokemon_2024.data.repository.PokedexDBrepository
import kotlinx.coroutines.launch
/*
class MainActivity : AppCompatActivity() {

    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var viewModel: PokedexViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()


        val databaseDriverFactory = DatabaseDriverFactory(this)
        val dbRepository = PokedexDBrepository(databaseDriverFactory)


        val viewModelFactory = PokedexViewModelFactory(dbRepository)


        viewModel = ViewModelProvider(this, viewModelFactory)[PokedexViewModel::class.java]


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.screenState.collect {
                    when (it) {
                        PokedexScreenState.Loading -> showLoading()
                        PokedexScreenState.Error -> handlerError()
                        is PokedexScreenState.ShowPokedex -> showPokedex(it.pokedex)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        pokedexAdapter = PokedexAdapter()

        val gridLayoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        with(binding.rvPokedex) {
            this.layoutManager = gridLayoutManager
            this.setHasFixedSize(true)
            this.adapter = pokedexAdapter
        }
    }

    private fun showPokedex(pokedex: Pokedex) {
        binding.pokedexProgressBar.visibility = View.GONE
        pokedexAdapter.updatePokedex(pokedex.results)
    }

    private fun handlerError() {
        Toast.makeText(this, "Error buscando la informacion", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        binding.pokedexProgressBar.visibility = View.VISIBLE
    }
}
class MainActivity : AppCompatActivity() {

    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var viewModel: PokedexViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // Crear el factory para el driver de la base de datos
        val databaseDriverFactory = DatabaseDriverFactory(this)

        // Crear el repositorio para la base de datos
        val dbRepository = PokedexDBrepository(databaseDriverFactory)

        // Crear el ViewModelFactory con el repositorio de la base de datos
        val viewModelFactory = PokedexViewModelFactory(dbRepository)

        // Obtener una instancia de PokedexViewModel usando ViewModelProvider
        viewModel = ViewModelProvider(this, viewModelFactory).get(PokedexViewModel::class.java)

        // Observar los cambios en el estado de la pantalla
        lifecycleScope.launch {
            viewModel.screenState.collect { state ->
                when (state) {
                    is PokedexScreenState.Loading -> showLoading()
                    is PokedexScreenState.Error -> {
                        handlerError()
                        viewModel.loadFromDatabase()
                    }
                    is PokedexScreenState.ShowPokedex -> showPokedex(state.pokedex)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        pokedexAdapter = PokedexAdapter()

        val gridLayoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        with(binding.rvPokedex) {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = pokedexAdapter
        }
    }

    private fun showPokedex(pokedex: Pokedex) {
        binding.pokedexProgressBar.visibility = View.GONE
        pokedexAdapter.updatePokedex(pokedex.results)
    }

    private fun handlerError() {
        Toast.makeText(this, "Error buscando la información", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        binding.pokedexProgressBar.visibility = View.VISIBLE
    }
}*/
class MainActivity : AppCompatActivity() {

    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var viewModel: PokedexViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // Crear el factory para el driver de la base de datos
        val databaseDriverFactory = DatabaseDriverFactory(this)

        // Crear el repositorio para la base de datos
        val dbRepository = PokedexDBrepository(databaseDriverFactory)

        // Crear el repositorio para la API
        val apiRepository = ApiPokedexRepository()

        // Crear el ViewModelFactory con los repositorios
        val viewModelFactory = PokedexViewModelFactory(apiRepository, dbRepository)

        // Obtener una instancia de PokedexViewModel usando ViewModelProvider
        viewModel = ViewModelProvider(this, viewModelFactory).get(PokedexViewModel::class.java)

        // Observar los cambios en el estado de la pantalla
        lifecycleScope.launch {
            viewModel.screenState.collect { state ->
                when (state) {
                    is PokedexScreenState.Loading -> showLoading()
                    is PokedexScreenState.Error -> {
                        handlerError()
                        viewModel.loadFromDatabase()
                    }
                    is PokedexScreenState.ShowPokedex -> showPokedex(state.pokedex)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        pokedexAdapter = PokedexAdapter()

        val gridLayoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        with(binding.rvPokedex) {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = pokedexAdapter
        }
    }

    private fun showPokedex(pokedex: Pokedex) {
        binding.pokedexProgressBar.visibility = View.GONE
        pokedexAdapter.updatePokedex(pokedex.results)
    }

    private fun handlerError() {
        Toast.makeText(this, "Error buscando la información", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        binding.pokedexProgressBar.visibility = View.VISIBLE
    }
}

