package com.marvel.characters.domain

import android.os.Build
import androidx.annotation.RequiresApi
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.repository.CharactersRepository


class CharactersUseCase constructor(
    private val repo: CharactersRepository,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun invoke(
        characterRequest: CharacterRequest,
        hash: String, ts: String
    ) = repo.getCharacters(characterRequest, hash = hash, ts = ts)
}