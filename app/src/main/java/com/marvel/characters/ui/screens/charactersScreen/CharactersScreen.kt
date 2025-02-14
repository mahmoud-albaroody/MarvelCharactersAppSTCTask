package com.marvel.characters.ui.screens.charactersScreen

import MarvelTopBar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
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
import com.marvel.characters.utils.sharedPreferences.getKeys


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CharactersScreen(navController: NavController, mainViewModel: MainViewModel) {
    var charactersList by rememberSaveable { mutableStateOf<List<Result>?>(null) }

    val context = LocalContext.current
    val limit = 10
    val offset = 1
    val characters =
        mainViewModel.characterPagingFlow.collectAsLazyPagingItems()

    // characters api call for first time
    LaunchedEffect(Unit) {
        if (mainViewModel.characters == null) {
            mainViewModel.charactersList(
                CharacterRequest(limit = limit, offset = offset),
                ApiURL.BASE_URL.plus("characters"),
                getKeys(context).first.toString(),
                getKeys(context).second.toString()
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








