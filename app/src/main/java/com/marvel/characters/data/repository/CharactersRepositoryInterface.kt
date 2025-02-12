package com.marvel.characters.data.repository

import androidx.paging.PagingData
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel

import kotlinx.coroutines.flow.Flow

interface CharactersRepositoryInterface {
    fun getCharactersPagingDataSource(characterRequest: CharacterRequest): Flow<PagingData<CharactersModel>>

}