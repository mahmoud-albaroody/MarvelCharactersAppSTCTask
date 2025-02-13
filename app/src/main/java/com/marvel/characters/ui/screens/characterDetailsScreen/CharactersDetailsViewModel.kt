package com.marvel.characters.ui.screens.characterDetailsScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.model.Result
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

    var comics by mutableStateOf<DataState<CharactersModel>?>(null)
        private set

    var series by mutableStateOf<DataState<CharactersModel>?>(null)
        private set

    var stores by mutableStateOf<DataState<CharactersModel>?>(null)
        private set

    var events by mutableStateOf<DataState<CharactersModel>?>(null)
        private set
    var urls by mutableStateOf<DataState<CharactersModel>?>(null)
        private set

    fun comicsList(characterRequest: CharacterRequest, url: String) {
        if (comics == null) {
            networkManager.changeBaseUrl(url)
            viewModelScope.launch {
                repo.invoke(characterRequest = characterRequest).onEach {
                    comics = it
                }.launchIn(viewModelScope)
            }
        }
    }

    fun seriesList(characterRequest: CharacterRequest, url: String) {
        if (series == null) {
            networkManager.changeBaseUrl(url)
            viewModelScope.launch {
                repo.invoke(characterRequest = characterRequest).onEach {
                    series = it
                }.launchIn(viewModelScope)
            }
        }
    }

    fun storesList(characterRequest: CharacterRequest, url: String) {
        if (stores == null) {
            networkManager.changeBaseUrl(url)
            viewModelScope.launch {
                repo.invoke(characterRequest = characterRequest).onEach {
                    stores = it
                }.launchIn(viewModelScope)
            }
        }
    }

    fun eventsList(characterRequest: CharacterRequest, url: String) {
        if (events == null) {
            networkManager.changeBaseUrl(url)
            viewModelScope.launch {
                repo.invoke(characterRequest = characterRequest).onEach {
                    events = it
                }.launchIn(viewModelScope)
            }
        }
    }


}