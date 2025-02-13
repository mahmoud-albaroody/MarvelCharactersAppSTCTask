package com.marvel.characters.ui.screens.mainScreen

import MarvelTopBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.marvel.characters.navigation.Navigation


@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    Box(modifier = Modifier.fillMaxWidth()) {
        Navigation(navController, Modifier, mainViewModel)

    }
}