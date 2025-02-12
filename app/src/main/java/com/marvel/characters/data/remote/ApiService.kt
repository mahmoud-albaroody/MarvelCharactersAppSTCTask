package com.marvel.characters.data.remote

import com.marvel.characters.data.model.CharactersModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("characters")
    suspend fun charactersList(
        @Query("apikey") apikey: String= ApiURL.PUBLIC_KEY,
        @Query("hash") hash: String? = "01efb57dfd9c19eec576b99dbbdf69f2",
        @Query("ts") ts: String? = "1739283699287",
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int? = null
    ): CharactersModel


}