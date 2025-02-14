package com.marvel.characters.ui.screens.mainScreen

import MarvelTopBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.marvel.characters.R
import com.marvel.characters.navigation.Navigation
import com.marvel.characters.utils.ntworkconnection.ConnectionState
import com.marvel.characters.utils.ntworkconnection.connectivityState
import kotlinx.coroutines.launch


@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    // internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(Color.Black)
    }
    LaunchedEffect(isConnected) {
        if (!isConnected) {
            scope.launch {
                snackbarHostState.showSnackbar(context.getString(R.string.no_network))
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            Navigation(navController, Modifier, mainViewModel)
        }
    }
}