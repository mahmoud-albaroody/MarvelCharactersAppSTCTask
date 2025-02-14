package com.marvel.characters.ui.screens.characterDetailsScreen

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.marvel.characters.R
import com.marvel.characters.data.model.CharacterRequest
import com.marvel.characters.data.model.CharactersModel
import com.marvel.characters.data.model.Result
import com.marvel.characters.navigation.Screen
import com.marvel.characters.ui.component.DescriptionSection
import com.marvel.characters.ui.component.HorizontalItemList
import com.marvel.characters.ui.component.TextRed
import com.marvel.characters.ui.component.TextWhite
import com.marvel.characters.ui.screens.mainScreen.MainViewModel
import com.marvel.characters.ui.theme.SecondaryFontColor
import com.marvel.characters.utils.network.DataState
import com.marvel.characters.utils.sharedPreferences.getKeys
import kotlinx.coroutines.delay


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CharacterDetailsScreen(
    navController: NavController, mainViewModel: MainViewModel,
    characterId: String?
) {
    val charactersDetailsViewModel = hiltViewModel<CharactersDetailsViewModel>()

    var charactersItem by remember { mutableStateOf(Result()) }
    val context = LocalContext.current
    var comicsUrl: String? = null
    var seriesUrl: String? = null
    var storesUrl: String? = null
    var eventsUrl: String? = null


    LaunchedEffect(key1 = 0) {
        characterId?.let {
            (mainViewModel.characters as DataState.Success<CharactersModel>)
                .data.data.results.let { results ->
                    if (results.find { it.id.toString() == characterId } != null)
                        charactersItem = results.find { it.id.toString() == characterId }!!
                    comicsUrl =
                        results.find { it.id.toString() == characterId }?.comics?.collectionURI
                    seriesUrl =
                        results.find { it.id.toString() == characterId }?.series?.collectionURI
                    storesUrl =
                        results.find { it.id.toString() == characterId }?.stories?.collectionURI
                    eventsUrl =
                        results.find { it.id.toString() == characterId }?.events?.collectionURI
                }

        }
        comicsUrl?.let {
            charactersDetailsViewModel
                .comicsList(
                    CharacterRequest(10, 1),
                    url = it,
                    getKeys(context).first.toString(),
                    getKeys(context).second.toString()
                )
        }
        delay(200)
        seriesUrl?.let {
            charactersDetailsViewModel
                .seriesList(
                    CharacterRequest(10, 1),
                    url = it,
                    getKeys(context).first.toString(),
                    getKeys(context).second.toString()
                )
        }
        delay(200)
        storesUrl?.let {
            charactersDetailsViewModel
                .storesList(
                    CharacterRequest(10, 1), url = it,
                    getKeys(context).first.toString(),
                    getKeys(context).second.toString()
                )
        }
        delay(300)
        eventsUrl?.let {
            charactersDetailsViewModel
                .eventsList(
                    CharacterRequest(10, 1),
                    url = it,
                    getKeys(context).first.toString(),
                    getKeys(context).second.toString()
                )
        }

    }

    Column {
        Box(
            Modifier
                .background(SecondaryFontColor)
        ) {

            LazyColumn(
                Modifier
                    .alpha(0.8f)
                    .fillMaxSize()
            ) {
                item {
                    DescriptionSection(characterItem = charactersItem)
                }

                item {
                    Column(Modifier.padding(horizontal = 8.dp)) {
                        charactersDetailsViewModel.comics.let {
                            if (it is DataState.Success<CharactersModel>) {

                                HorizontalItemList(
                                    items = it.data.data.results as ArrayList,
                                    text = stringResource(R.string.comics),
                                    onItemClick = {
                                        it.images?.map { image ->
                                            image.title = it.title.toString()
                                        }
                                        val imagesJson = Uri.encode(Gson().toJson(it.images))
                                        navController.navigate(
                                            Screen.CharacterImagePreview.route.plus(
                                                "/${imagesJson}"
                                            )
                                        )
                                    })

                            }

                        }
                        charactersDetailsViewModel.series.let {
                            if (it is DataState.Success<CharactersModel>) {
                                HorizontalItemList(
                                    items = it.data.data.results as ArrayList,
                                    text = stringResource(R.string.series),
                                    onItemClick = {
                                        it.images?.map { image ->
                                            image.title = it.title.toString()
                                        }
                                        val imagesJson = Uri.encode(Gson().toJson(it.images))
                                        navController.navigate(
                                            Screen.CharacterImagePreview.route.plus(
                                                "/${imagesJson}"
                                            )
                                        )
                                    })


                            }
                        }
                        charactersDetailsViewModel.stores.let {
                            if (it is DataState.Success<CharactersModel>) {
                                HorizontalItemList(
                                    items = it.data.data.results as ArrayList,
                                    text = stringResource(R.string.stories),
                                    onItemClick = {
                                        it.images?.map { image ->
                                            image.title = it.title.toString()
                                        }
                                        val imagesJson = Uri.encode(Gson().toJson(it.images))
                                        navController.navigate(
                                            Screen.CharacterImagePreview.route.plus(
                                                "/${imagesJson}"
                                            )
                                        )
                                    })


                            }
                        }
                        charactersDetailsViewModel.events.let {
                            if (it is DataState.Success<CharactersModel>
                                && it.data.data.results.isNotEmpty()
                            ) {
                                HorizontalItemList(
                                    items = it.data.data.results as ArrayList,
                                    text = stringResource(R.string.events),
                                    onItemClick = {
                                        it.images?.map { image ->
                                            image.title = it.title.toString()
                                        }
                                        val imagesJson = Uri.encode(Gson().toJson(it.images))
                                        navController.navigate(
                                            Screen.CharacterImagePreview.route.plus(
                                                "/${imagesJson}"
                                            )
                                        )
                                    })


                            }
                        }
                    }
                }

                charactersItem.urls?.let {
                    item {
                        Column(
                            Modifier
                                .padding(horizontal = 8.dp)
                                .padding(top = 8.dp)
                        ) {
                            Spacer(modifier = Modifier.padding(bottom = 8.dp))
                            TextRed(text = stringResource(R.string.related_links))
                            Spacer(modifier = Modifier.padding(bottom = 16.dp))
                            it.map {

                                Box(Modifier.padding(vertical = 10.dp)) {
                                    Row(
                                        Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        TextWhite(text = it.type, fontSize = 20)
                                        Image(
                                            painter = painterResource(R.drawable.baseline_arrow_forward_ios_24),
                                            contentDescription = ""
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.padding(bottom = 50.dp))
                        }


                    }
                }

            }
            Image(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 16.dp)
                    .clickable {
                        navController.popBackStack()
                    },
                painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                contentDescription = ""
            )
        }
    }
}





