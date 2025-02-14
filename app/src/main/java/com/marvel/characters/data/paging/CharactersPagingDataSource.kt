package com.marvel.characters.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marvel.characters.data.model.CharacterItem
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.remote.ApiService
import com.marvel.characters.data.remote.ApiURL
import com.marvel.characters.utils.NetworkManager
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharactersPagingDataSource @Inject constructor(private val apiService: ApiService,
                    private val networkManager: NetworkManager) : PagingSource<Int, CharacterItem>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterItem> {
        return try {
            val nextPage = params.key ?: 1
            networkManager.changeBaseUrl(ApiURL.BASE_URL.plus("characters"))
            val charactersList = apiService.charactersList(limit = 10, offset = nextPage)
            LoadResult.Page(
                data = charactersList.data.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey =  if (charactersList.data.results.isNotEmpty()) charactersList.data.offset + 1 else  null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (httpException: HttpException) {
            return LoadResult.Error(httpException)
        }
    }
}