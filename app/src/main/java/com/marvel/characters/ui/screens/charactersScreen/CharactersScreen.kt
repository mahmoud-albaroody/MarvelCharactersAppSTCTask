package com.marvel.characters.ui.screens.charactersScreen

import MarvelTopBar
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.model.Result
import com.marvel.characters.data.remote.ApiURL
import com.marvel.characters.navigation.Screen
import com.marvel.characters.ui.component.CharacterItemList
import com.marvel.characters.ui.screens.mainScreen.MainViewModel
import com.marvel.characters.utils.network.DataState


@Composable
fun CharactersScreen(navController: NavController, mainViewModel: MainViewModel) {
    var charactersList by rememberSaveable { mutableStateOf<List<Result>?>(null) }


    val limit = 10
    val offset = 1
    val characters = mainViewModel.characterPagingFlow.collectAsLazyPagingItems()

    // characters api call for first time
    LaunchedEffect(Unit) {
            if (mainViewModel.characters == null) {
                mainViewModel.charactersList(
                    CharacterRequest(limit = limit, offset = offset),
                    ApiURL.BASE_URL.plus("characters")
                )
            }
    }

    Column {
        MarvelTopBar()
        when (mainViewModel.characters) {
            is DataState.Success -> {
                charactersList =
                    (mainViewModel.characters as DataState.Success<CharactersModel>)
                        .data.data.results as ArrayList
                CharacterItemList(
                    characters,
                    characterList = charactersList as java.util.ArrayList<Result>,
                    onItemClick = {
                        navController.navigate(
                            Screen.CharacterDetails.route.plus(
                                "/${it.id}"
                            )
                        )
                    },

                    )
            }

            else -> {

            }
        }
    }

}








