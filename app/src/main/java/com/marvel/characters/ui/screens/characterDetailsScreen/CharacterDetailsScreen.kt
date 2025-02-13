package com.marvel.characters.ui.screens.characterDetailsScreen

import android.util.Log
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.model.Result
import com.marvel.characters.ui.component.CharacterItemList
import com.marvel.characters.ui.component.HorizontalItemList
import com.marvel.characters.ui.screens.charactersScreen.CharactersViewModel
import com.marvel.characters.ui.screens.mainScreen.MainViewModel
import com.marvel.characters.utils.network.DataState
import com.marvel.characters.utils.ntworkconnection.ConnectionState
import com.marvel.characters.utils.ntworkconnection.connectivityState
import kotlinx.coroutines.delay


@Composable
fun CharacterDetailsScreen(
    navController: NavController, mainViewModel: MainViewModel,
    characterId: String?
) {
    val charactersList = remember { mutableStateOf(arrayListOf<Result>()) }
    val comicsList = remember { mutableStateOf(arrayListOf<Result>()) }
    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    val charactersDetailsViewModel = hiltViewModel<CharactersDetailsViewModel>()

    characterId?.let {
        val comicsUrl = (mainViewModel.characters.value as DataState.Success<CharactersModel>)
            .data.data.results.find { it.id.toString() == characterId }?.comics?.collectionURI

    LaunchedEffect(key1 = 0) {

            delay(1200)
            comicsUrl?.let {
                charactersDetailsViewModel
                    .comicsList(CharacterRequest(10, 1), url = comicsUrl)
            }
        }
    }


    if (charactersDetailsViewModel.comics.value is DataState.Success<CharactersModel>) {
        comicsList.value =
            (charactersDetailsViewModel.comics.value as DataState.Success<CharactersModel>)
                .data.data.results as ArrayList
        HorizontalItemList(items = comicsList.value, text = "", onItemClick = {

        })
    }
}




