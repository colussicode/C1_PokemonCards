package com.example.c1_pokemoncards.presentation.home

import androidx.lifecycle.*
import com.example.c1_pokemoncards.data.models.Pokemon
import com.example.c1_pokemoncards.data.remote.service.PokemonService
import kotlinx.coroutines.launch

class HomeViewModel(
    private val service: PokemonService
) : ViewModel(){

    private val _uiState = MutableLiveData<UiState>(UiState.Loading)
    val uiState: LiveData<UiState> = _uiState

    fun getPokemonList() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val data = service.getData()
                _uiState.value = UiState.Resume(data.cards)
            } catch (e: Throwable) {
                _uiState.value = UiState.Error(e)
            }
        }
    }
}

class HomeViewModelFactory(private val service: PokemonService) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = HomeViewModel(service) as T
}

sealed class UiState {
    data class Resume(val pokemonList: List<Pokemon>): UiState()
    data class Error(val error: Throwable): UiState()
    object Loading: UiState()
}