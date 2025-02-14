package com.marvel.characters.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.marvel.characters.ui.screens.characterDetailsScreen.CharacterDetailsScreen
import com.marvel.characters.ui.screens.characterImagesPrivewScreen.CharacterImagePreview
import com.marvel.characters.ui.screens.charactersScreen.CharactersScreen
import com.marvel.characters.ui.screens.mainScreen.MainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier, mainViewModel: MainViewModel
) {
    AnimatedNavHost(navController, startDestination = Screen.Characters.route, modifier) {

        composable(

            Screen.CharacterDetails.route.plus(Screen.CharacterDetails.objectPath),
            arguments = listOf(navArgument(Screen.CharacterDetails.objectName) {
                type = NavType.StringType
            }),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 1000 },
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -1000 },
                    animationSpec = tween(1000)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -1000 },
                    animationSpec = tween(1000)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { 1000 },
                    animationSpec = tween(500)
                )
            }
        ) { backStack ->
            val characterId = backStack.arguments?.getString(Screen.CharacterDetails.objectName)
            characterId?.let {
                CharacterDetailsScreen(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    characterId
                )
            }
        }
        composable(
            Screen.CharacterImagePreview.route.plus(Screen.CharacterImagePreview.objectPath)
        ) { backStack ->
            val images = backStack.arguments?.getString(Screen.CharacterImagePreview.objectName)
            Log.e("dddddd", images.toString())
            images?.let {
                CharacterImagePreview(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    images
                )
            }
        }

        composable(Screen.Characters.route) {
            CharactersScreen(
                navController = navController,
                mainViewModel = mainViewModel
            )
        }

    }
}


@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
