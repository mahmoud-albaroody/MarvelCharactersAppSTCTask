package com.marvel.characters.data.remote

import com.marvel.characters.data.model.CharactersModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("characters")
    suspend fun charactersList(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String?,
        @Query("ts") ts: String = System.currentTimeMillis().toString(),
        @Query("limit") limit: String?,
        @Query("offset") offset: String?
    ): CharactersModel


}