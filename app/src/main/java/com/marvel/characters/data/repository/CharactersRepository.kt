package com.marvel.characters.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.marvel.characters.data.model.CharacterItem
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.paging.CharactersPagingDataSource
import com.marvel.characters.data.remote.ApiService
import com.marvel.characters.utils.network.DataState
import com.marvel.characters.utils.sharedPreferences.getKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val apiService: ApiService
) : CharactersRepositoryInterface {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun getCharacters(characterRequest: CharacterRequest,
                               hash:String, ts:String):
            Flow<DataState<CharactersModel>> = flow {
        emit(DataState.Loading)
        try {
            val artistResult = apiService.charactersList(
                limit = characterRequest.limit,
                offset = characterRequest.offset,
                hash = hash ,
                ts = ts
            )
            emit(DataState.Success(artistResult))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }


    }

}