package com.marvel.characters.ui.screens.charactersScreen

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.model.Result
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
    val charactersList = remember { mutableStateOf(arrayListOf<Result>()) }


    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    // characters api call for first time
    LaunchedEffect(key1 = 0) {
      //  mainViewModel.charactersList(CharacterRequest(10, 1))
    }


    PreviewImageGallery()
//   if (mainViewModel.characters.value is DataState.Success<CharactersModel>) {
//        charactersList.value =
//            (mainViewModel.characters.value as DataState.Success<CharactersModel>)
//                .data.data.results as ArrayList
//        CharacterItemList(
//            characterList = charactersList.value,
//            onItemClick = {
//                navController.navigate(
//                    Screen.CharacterDetails.route.plus(
//                        "/${it.id}"
//                    )
//                )
//            }
//        )
//    }
}

@Composable
fun PreviewImageGallery() {
    val images = listOf(
        "http://i.annihil.us/u/prod/marvel/i/mg/3/30/611e6f42d0681.jpg",
        "http://i.annihil.us/u/prod/marvel/i/mg/3/30/611e6f42d0681.jpg",
        "http://i.annihil.us/u/prod/marvel/i/mg/3/30/611e6f42d0681.jpg"
    )

    var showGallery by remember { mutableStateOf(true) }

    if (showGallery) {
        DiscreteImageViewer(images) {
            showGallery = false
        }
    }
}




