package com.example.c1_pokemoncards.presentation.home

import androidx.lifecycle.*
import com.example.c1_pokemoncards.data.local.PokemonDao
import com.example.c1_pokemoncards.data.local.PokemonEntity
import com.example.c1_pokemoncards.data.models.Pokemon
import com.example.c1_pokemoncards.data.remote.service.PokemonService
import kotlinx.coroutines.launch

class HomeViewModel(
    private val service: PokemonService,
    private val pokemonDao: PokemonDao
) : ViewModel(){

    private val _uiState = MutableLiveData<UiState>(UiState.Loading(false))
    val uiState: LiveData<UiState> = _uiState

    init {
        getPokemonList()
        getPokemonsFromDb()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading(true)
            try {
                val data = service.getData()
                insertPokemonsToDb(data.cards)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message!!)
            } finally {
                _uiState.value = UiState.Loading(false)
            }
        }
    }

    private fun insertPokemonsToDb(pokemonList: List<PokemonEntity>) {
        viewModelScope.launch {
            pokemonDao.deleteAll()
            pokemonDao.insertAll(pokemonList)
        }
    }

    private fun getPokemonsFromDb() {
        viewModelScope.launch {
            try {
                val data = pokemonDao.getAll()
                _uiState.postValue(UiState.Resume(data))
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message!!)
            }
        }
    }
}

class HomeViewModelFactory(private val service: PokemonService, private val pokemonDao: PokemonDao) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = HomeViewModel(service, pokemonDao) as T
}

sealed class UiState {
    data class Resume(val pokemonList: List<PokemonEntity>): UiState()
    data class Error(val error: String): UiState()
    data class Loading(val isLoading: Boolean): UiState()
}