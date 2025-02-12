package com.marvel.characters.data.repository

import androidx.paging.PagingData
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.remote.ApiService
import com.marvel.characters.utils.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val apiService: ApiService
) : CharactersRepositoryInterface {


    override fun getCharacters(characterRequest: CharacterRequest):
            Flow<DataState<CharactersModel>> = flow {
        emit(DataState.Loading)
        try {
            val artistResult = apiService.charactersList(
                limit = characterRequest.limit,
                offset = characterRequest.offset
            )
            emit(DataState.Success(artistResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }


    }
}