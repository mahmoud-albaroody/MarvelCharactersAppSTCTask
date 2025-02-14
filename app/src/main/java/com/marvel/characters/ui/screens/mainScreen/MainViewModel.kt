package com.marvel.characters.ui.screens.mainScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.marvel.characters.data.model.CharacterItem
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.paging.CharactersPagingDataSource
import com.marvel.characters.data.remote.ApiService
import com.marvel.characters.domain.CharactersUseCase
import com.marvel.characters.utils.NetworkManager
import com.marvel.characters.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: CharactersUseCase,
                                        private val api:ApiService,
                                        private val networkManager: NetworkManager
) : ViewModel() {
    var characters by mutableStateOf<DataState<CharactersModel>?>(null)
        private set
    fun charactersList(characterRequest: CharacterRequest,url:String) {
        if (characters == null) {
            networkManager.changeBaseUrl(url)
            viewModelScope.launch {
                repo.invoke(characterRequest = characterRequest).onEach {
                    characters = it
                }.launchIn(viewModelScope)
            }
        }
    }
    val characterPagingFlow: Flow<PagingData<CharacterItem>> = Pager(
        config = PagingConfig(
            pageSize = 10, // Number of items per page
            prefetchDistance = 2, // How many items before next load
            enablePlaceholders = false
        ),
        pagingSourceFactory = { CharactersPagingDataSource(api,networkManager) }
    ).flow.cachedIn(viewModelScope)
}