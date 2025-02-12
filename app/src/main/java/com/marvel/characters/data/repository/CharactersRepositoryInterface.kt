package com.marvel.characters.data.repository

import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.utils.network.DataState

import kotlinx.coroutines.flow.Flow

interface CharactersRepositoryInterface {
    fun getCharacters(characterRequest: CharacterRequest): Flow<DataState<CharactersModel>>
}