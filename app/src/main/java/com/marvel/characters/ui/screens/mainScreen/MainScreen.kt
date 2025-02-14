package com.marvel.characters.ui.screens.mainScreen

import MarvelTopBar
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.marvel.characters.data.remote.ApiURL.PRIVATE_KEY
import com.marvel.characters.data.remote.ApiURL.PUBLIC_KEY
import com.marvel.characters.data.remote.generateMD5Hash
import com.marvel.characters.data.remote.generateTs
import com.marvel.characters.navigation.Navigation
import com.marvel.characters.utils.ntworkconnection.ConnectionState
import com.marvel.characters.utils.ntworkconnection.connectivityState
import com.marvel.characters.utils.sharedPreferences.getKeys
import com.marvel.characters.utils.sharedPreferences.saveKeys
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
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
    mainViewModel.ts = getKeys(context).second.toString()
    mainViewModel.hash = getKeys(context).first.toString()
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(Color.Black)
        systemUiController.setNavigationBarColor(Color.Black)
    }
    LaunchedEffect(isConnected) {
       saveKeys(context,
           generateMD5Hash(generateTs(),PRIVATE_KEY,PUBLIC_KEY),
           generateTs())
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