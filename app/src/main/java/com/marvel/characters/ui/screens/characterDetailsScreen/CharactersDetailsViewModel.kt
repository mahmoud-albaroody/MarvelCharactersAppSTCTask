package com.marvel.characters.ui.screens.characterDetailsScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.domain.CharactersUseCase
import com.marvel.characters.utils.NetworkManager
import com.marvel.characters.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersDetailsViewModel
@Inject constructor(
    private val repo: CharactersUseCase,
    private val networkManager: NetworkManager
) : ViewModel() {
    val comics: MutableState<DataState<CharactersModel>?> = mutableStateOf(null)
    val series: MutableState<DataState<CharactersModel>?> = mutableStateOf(null)
    val stores: MutableState<DataState<CharactersModel>?> = mutableStateOf(null)
    val events: MutableState<DataState<CharactersModel>?> = mutableStateOf(null)
    val urls: MutableState<DataState<CharactersModel>?> = mutableStateOf(null)

    fun comicsList(characterRequest: CharacterRequest, url: String) {
        networkManager.changeBaseUrl(url)
        viewModelScope.launch {
            repo.invoke(characterRequest = characterRequest).onEach {
                comics.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun seriesList(characterRequest: CharacterRequest, url: String) {
        networkManager.changeBaseUrl(url)
        viewModelScope.launch {
            repo.invoke(characterRequest = characterRequest).onEach {
                series.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun storesList(characterRequest: CharacterRequest, url: String) {
        networkManager.changeBaseUrl(url)
        viewModelScope.launch {
            repo.invoke(characterRequest = characterRequest).onEach {
                stores.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun eventsList(characterRequest: CharacterRequest, url: String) {
        networkManager.changeBaseUrl(url)
        viewModelScope.launch {
            repo.invoke(characterRequest = characterRequest).onEach {
                events.value = it
            }.launchIn(viewModelScope)
        }
    }


}