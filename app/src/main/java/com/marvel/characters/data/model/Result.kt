package com.marvel.characters.data.model

typealias CharacterItem = Result
typealias ComicsItem = Result
data class Result(
    val comics: Stories?=null,
    val description: String?=null,
    val events: Stories?=null,
    val id: Int?=null,
    val modified: String?=null,
    val name: String?=null,
    val title:String?=null,
    val resourceURI: String?=null,
    val series: Stories?=null,
    val stories: Stories?=null,
    val thumbnail: Thumbnail?=null,
    val urls: List<Url>?=null,
    val images:ArrayList<Thumbnail>?=null
)