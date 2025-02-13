package com.marvel.characters.ui.screens.characterDetailsScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.model.Result
import com.marvel.characters.ui.component.DescriptionSection
import com.marvel.characters.ui.component.HorizontalItemList
import com.marvel.characters.ui.component.TextRed
import com.marvel.characters.ui.component.TextWhite
import com.marvel.characters.ui.screens.mainScreen.MainViewModel
import com.marvel.characters.ui.theme.SecondaryFontColor
import com.marvel.characters.utils.network.DataState
import com.marvel.characters.utils.ntworkconnection.ConnectionState
import com.marvel.characters.utils.ntworkconnection.connectivityState
import kotlinx.coroutines.delay


@Composable
fun CharacterDetailsScreen(
    navController: NavController, mainViewModel: MainViewModel,
    characterId: String?
) {
    val charactersDetailsViewModel = hiltViewModel<CharactersDetailsViewModel>()

    val charactersList = remember { mutableStateOf(arrayListOf<Result>()) }
    val charactersItem = remember { mutableStateOf<Result>(Result()) }
    val comicsList = charactersDetailsViewModel.comics
    val seriesList = charactersDetailsViewModel.series
    val storesList = charactersDetailsViewModel.stores
    val eventsList = charactersDetailsViewModel.events
    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    var comicsUrl: String? = null
    var seriesUrl: String? = null
    var storesUrl: String? = null
    var eventsUrl: String? = null
    characterId?.let {
        charactersItem.value =
            (mainViewModel.characters.value as DataState.Success<CharactersModel>)
                .data.data.results.find { it.id.toString() == characterId }!!
        comicsUrl = (mainViewModel.characters.value as DataState.Success<CharactersModel>)
            .data.data.results.find { it.id.toString() == characterId }?.comics?.collectionURI
        seriesUrl = (mainViewModel.characters.value as DataState.Success<CharactersModel>)
            .data.data.results.find { it.id.toString() == characterId }?.series?.collectionURI
        storesUrl = (mainViewModel.characters.value as DataState.Success<CharactersModel>)
            .data.data.results.find { it.id.toString() == characterId }?.stories?.collectionURI
        eventsUrl = (mainViewModel.characters.value as DataState.Success<CharactersModel>)
            .data.data.results.find { it.id.toString() == characterId }?.events?.collectionURI
    }

    LaunchedEffect(key1 = 0) {
        comicsUrl?.let {
            charactersDetailsViewModel
                .comicsList(CharacterRequest(10, 1), url = it)
        }
        delay(200)
        seriesUrl?.let {
            charactersDetailsViewModel
                .seriesList(CharacterRequest(10, 1), url = it)
        }
        delay(200)
        storesUrl?.let {
            charactersDetailsViewModel
                .storesList(CharacterRequest(10, 1), url = it)
        }
        delay(200)
        eventsUrl?.let {
            charactersDetailsViewModel
                .eventsList(CharacterRequest(10, 1), url = it)
        }

    }

    LazyColumn(
        Modifier
            .background(SecondaryFontColor)

            .fillMaxSize()
    ) {
        item {
            Box (Modifier.padding(horizontal = 6.dp)){
                DescriptionSection(characterItem = charactersItem.value)
            }
        }

        item {
            Column(Modifier.padding(horizontal = 6.dp)) {


                charactersDetailsViewModel.comics.value.let {
                    if (it is DataState.Success<CharactersModel>) {
                        Log.e("ssss", it.data.data.results.toString())

                        HorizontalItemList(
                            items = it.data.data.results as ArrayList,
                            text = "COMICS",
                            onItemClick = {

                            })

                    }

                }
                charactersDetailsViewModel.series.value.let {
                    if (it is DataState.Success<CharactersModel>) {
                        HorizontalItemList(
                            items = it.data.data.results as ArrayList,
                            text = "SERIES",
                            onItemClick = {

                            })


                    }
                }
                charactersDetailsViewModel.stores.value.let {
                    if (it is DataState.Success<CharactersModel>) {
                        HorizontalItemList(
                            items = it.data.data.results as ArrayList,
                            text = "STORIES",
                            onItemClick = {

                            })


                    }
                }
                charactersDetailsViewModel.events.value.let {
                    if (it is DataState.Success<CharactersModel>) {
                        HorizontalItemList(
                            items = it.data.data.results as ArrayList,
                            text = "EVENTS",
                            onItemClick = {

                            })


                    }
                }
            }
        }

        item {
            Column(
                Modifier
                    .padding(horizontal = 6.dp)) {
                TextRed(text = "RELATED LINKS")
                charactersItem.value.urls?.forEach {
                    Box(Modifier.padding(vertical = 10.dp)) {
                        TextWhite(text = it.type, fontSize = 20)
                    }
                }
            }


        }
    }
}






