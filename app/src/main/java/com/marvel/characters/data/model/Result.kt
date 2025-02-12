package com.marvel.characters.data.model

typealias CharacterItem = Result

data class Result(
    val comics: Comics?=null,
    val description: String?=null,
    val events: Events?=null,
    val id: Int?=null,
    val modified: String?=null,
    val name: String?=null,
    val resourceURI: String?=null,
    val series: Series?=null,
    val stories: Stories?=null,
    val thumbnail: Thumbnail?=null,
    val urls: List<Url>?=null
)