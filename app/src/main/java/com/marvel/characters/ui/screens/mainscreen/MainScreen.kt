package com.marvel.characters.ui.screens.mainscreen

import android.util.Log
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.model.Result
import com.marvel.characters.ui.component.CharacterItemList
import com.marvel.characters.utils.network.DataState
import com.marvel.characters.utils.ntworkconnection.ConnectionState
import com.marvel.characters.utils.ntworkconnection.connectivityState

import kotlinx.coroutines.delay


@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val charactersList = remember { mutableStateOf(arrayListOf<Result>()) }
    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available

    // characters api call for first time
    LaunchedEffect(key1 = 0) {
        delay(1200)
        mainViewModel.charactersList(CharacterRequest(10, 1))
    }


    if (mainViewModel.characters.value is DataState.Success<CharactersModel>) {
        charactersList.value =
            (mainViewModel.characters.value as DataState.Success<CharactersModel>)
                .data.data.results as ArrayList
        CharacterItemList(
            characterList = charactersList.value,
            navController = navController
        )
    }
}




