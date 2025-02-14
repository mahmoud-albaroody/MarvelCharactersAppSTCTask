package com.marvel.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marvel.characters.data.model.CharacterItem
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.utils.network.DataState

import kotlinx.coroutines.flow.Flow

interface CharactersRepositoryInterface {
    fun getCharacters(characterRequest: CharacterRequest): Flow<DataState<CharactersModel>>


//    fun getCharacters(characterRequest: CharacterRequest): Flow<PagingData<CharacterItem>>
}