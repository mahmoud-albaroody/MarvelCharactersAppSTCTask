package com.marvel.characters.data.repository

import androidx.paging.PagingData
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val apiService: ApiService
) : CharactersRepositoryInterface {


    override fun getCharactersPagingDataSource(characterRequest: CharacterRequest): Flow<PagingData<CharactersModel>> {
        TODO("Not yet implemented")
    }


}