package com.marvel.characters.data.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.marvel.characters.data.model.CharactersModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @RequiresApi(Build.VERSION_CODES.O)
    @GET("characters")
    suspend fun charactersList(
        @Query("apikey") apikey: String = ApiURL.PUBLIC_KEY,
        @Query("hash") hash: String?,
        @Query("ts") ts: String? ,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int? = null
    ): CharactersModel
}