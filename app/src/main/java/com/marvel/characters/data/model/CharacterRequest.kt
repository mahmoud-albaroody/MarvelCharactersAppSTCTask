package com.marvel.characters.data.model

data class CharacterRequest(
    val apikey: String,
    val hash: String,
    val ts: String,
    val limit: String,
    val offset: String
)
