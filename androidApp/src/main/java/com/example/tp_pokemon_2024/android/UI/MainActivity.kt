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
import com.example.tp_pokemon_2024.repository.ApiPokedexRepository
import com.example.tp_pokemon_2024.repository.PokedexDBrepository
import kotlinx.coroutines.launch

//class MainActivity : AppCompatActivity() {
//
//    private lateinit var pokedexAdapter: PokedexAdapter
//    private lateinit var viewModel: PokedexViewModel
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setupRecyclerView()
//
//        val databaseDriverFactory = DatabaseDriverFactory(this)
//
//        val dbRepository = PokedexDBrepository(databaseDriverFactory)
//
//        val apiRepository = ApiPokedexRepository()
//
//        val viewModelFactory = PokedexViewModelFactory(apiRepository, dbRepository)
//
//            viewModel = ViewModelProvider(this, viewModelFactory).get(PokedexViewModel::class.java)
//
//               lifecycleScope.launch {
//            viewModel.screenState.collect { state ->
//                when (state) {
//                    is PokedexScreenState.Loading -> showLoading()
//                    is PokedexScreenState.Error -> {
//                        handlerError()
//                        viewModel.loadFromDatabase()
//                    }
//                    is PokedexScreenState.ShowPokedex -> showPokedex(state.pokedex)
//                }
//            }
//        }
//    }
//
//    private fun setupRecyclerView() {
//        pokedexAdapter = PokedexAdapter()
//
//        val gridLayoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
//        with(binding.rvPokedex) {
//            layoutManager = gridLayoutManager
//            setHasFixedSize(true)
//            adapter = pokedexAdapter
//        }
//    }
//
//    private fun showPokedex(pokedex: Pokedex) {
//        binding.pokedexProgressBar.visibility = View.GONE
//        pokedexAdapter.updatePokedex(pokedex.results)
//    }
//
//    private fun handlerError() {
//        Toast.makeText(this, "Error buscando la información", Toast.LENGTH_LONG).show()
//    }
//
//    private fun showLoading() {
//        binding.pokedexProgressBar.visibility = View.VISIBLE
//    }
//}

class MainActivity : AppCompatActivity() {

    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var viewModel: PokedexViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupRetryButton()

        val databaseDriverFactory = DatabaseDriverFactory(this)

        val dbRepository = PokedexDBrepository(databaseDriverFactory)

        val apiRepository = ApiPokedexRepository()

        val viewModelFactory = PokedexViewModelFactory(apiRepository, dbRepository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(PokedexViewModel::class.java)

        lifecycleScope.launch {
            viewModel.screenState.collect { state ->
                when (state) {
                    is PokedexScreenState.Loading -> showLoading()
                    is PokedexScreenState.Error -> {
                        showError()
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

    private fun setupRetryButton() {
        binding.retryButton.setOnClickListener {
            showLoading()
            viewModel.loadPokedex()
        }
    }

    private fun showPokedex(pokedex: Pokedex) {
        binding.pokedexProgressBar.visibility = View.GONE
        binding.errorLayout.visibility = View.GONE
        binding.rvPokedex.visibility = View.VISIBLE
        pokedexAdapter.updatePokedex(pokedex.results)
    }

    private fun showError() {
        binding.pokedexProgressBar.visibility = View.GONE
        binding.rvPokedex.visibility = View.GONE
        binding.errorLayout.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.pokedexProgressBar.visibility = View.VISIBLE
        binding.rvPokedex.visibility = View.GONE
        binding.errorLayout.visibility = View.GONE
    }
}
