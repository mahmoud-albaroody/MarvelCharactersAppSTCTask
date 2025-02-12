package com.marvel.characters.domain

import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.repository.CharactersRepository


class CharactersUseCase constructor(
    private val repo: CharactersRepository,
) {
    suspend fun invoke(characterRequest: CharacterRequest)
    = repo.getCharacters(characterRequest)
}