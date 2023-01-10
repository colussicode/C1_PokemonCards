package com.example.c1_pokemoncards.presentation.home

import androidx.lifecycle.*
import com.example.c1_pokemoncards.data.models.Pokemon
import com.example.c1_pokemoncards.data.models.PokemonResult
import com.example.c1_pokemoncards.data.remote.service.PokemonService
import kotlinx.coroutines.launch

class HomeViewModel(
    private val service: PokemonService
) : ViewModel(){

    private val _pokemonList = MutableLiveData<List<Pokemon>>(emptyList())
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _uiState = MutableLiveData<UiState>(UiState.Resume)
    val uiState: LiveData<UiState> = _uiState

    fun getPokemonList() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading(isLoading = true)
            try {
                _uiState.value = UiState.Resume
                val data = service.getData()
                _pokemonList.value = data.cards
            } catch (e: Throwable) {
                _uiState.value = UiState.Error(e)
            } finally {
                _uiState.value = UiState.Loading(isLoading = false)
            }
        }
    }
}

class HomeViewModelFactory(private val service: PokemonService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = HomeViewModel(service) as T
}

sealed class UiState {
    object Resume: UiState()
    data class Error(val error: Throwable): UiState()
    data class Loading(val isLoading: Boolean): UiState()
}