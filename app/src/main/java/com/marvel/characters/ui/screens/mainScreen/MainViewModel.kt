package com.marvel.characters.ui.screens.mainScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.domain.CharactersUseCase
import com.marvel.characters.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: CharactersUseCase) : ViewModel() {
    val characters: MutableState<DataState<CharactersModel>?> = mutableStateOf(null)

    fun charactersList(characterRequest: CharacterRequest) {
        viewModelScope.launch {
            repo.invoke(characterRequest = characterRequest).onEach {
                characters.value = it
            }.launchIn(viewModelScope)
        }
    }

}