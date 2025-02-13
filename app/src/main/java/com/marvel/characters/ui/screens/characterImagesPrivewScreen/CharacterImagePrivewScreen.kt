package com.marvel.characters.ui.screens.characterImagesPrivewScreen

import android.provider.MediaStore.Images
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marvel.characters.data.model.Thumbnail
import com.marvel.characters.ui.component.DiscreteImageViewer
import com.marvel.characters.ui.screens.mainScreen.MainViewModel


@Composable
fun CharacterImagePreview(
    navController: NavController,
    mainViewModel: MainViewModel,
    imagesJson: String?
) {
    val listType = object : TypeToken<List<Thumbnail>>() {}.type
    val images: List<Thumbnail> = remember { Gson().fromJson(imagesJson, listType) ?: emptyList() }
    if (images.isNotEmpty())
        DiscreteImageViewer(images) {
            navController.popBackStack()
        }
}

