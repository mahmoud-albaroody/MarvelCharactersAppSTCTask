package com.marvel.characters.ui.screens.charactersScreen

import MarvelTopBar
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.model.Result
import com.marvel.characters.data.remote.ApiURL
import com.marvel.characters.navigation.Screen
import com.marvel.characters.ui.component.CharacterItemList
import com.marvel.characters.ui.component.DiscreteImageViewer
import com.marvel.characters.ui.screens.mainScreen.MainViewModel
import com.marvel.characters.utils.network.DataState
import com.marvel.characters.utils.ntworkconnection.ConnectionState
import com.marvel.characters.utils.ntworkconnection.connectivityState

import kotlinx.coroutines.delay


@Composable
fun CharactersScreen(navController: NavController, mainViewModel: MainViewModel) {
    var charactersList by rememberSaveable { mutableStateOf<List<Result>?>(null) }

    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    // characters api call for first time
    LaunchedEffect(Unit) {
        if (mainViewModel.characters == null) {
            mainViewModel.charactersList(
                CharacterRequest(10, 1),
                ApiURL.BASE_URL.plus("characters")
            )
        }
    }

    Column {
        MarvelTopBar()

        if (mainViewModel.characters is DataState.Success<CharactersModel>) {
            charactersList =
                (mainViewModel.characters as DataState.Success<CharactersModel>)
                    .data.data.results as ArrayList
            CharacterItemList(
                characterList = charactersList as java.util.ArrayList<Result>,
                onItemClick = {
                    navController.navigate(
                        Screen.CharacterDetails.route.plus(
                            "/${it.id}"
                        )
                    )
                }
            )
        }
    }
}






