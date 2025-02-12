package com.marvel.characters.navigation

import androidx.annotation.StringRes
import com.marvel.characters.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_name,
    val objectName: String = "",
    val objectPath: String = ""
) {

    data object Home : Screen("home_screen")


}