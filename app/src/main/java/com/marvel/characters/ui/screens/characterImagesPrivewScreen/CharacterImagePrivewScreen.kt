package com.marvel.characters.ui.screens.characterImagesPrivewScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marvel.characters.R
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
    if (images.isNotEmpty()) {
        DiscreteImageViewer(images) {
            navController.popBackStack()
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                text = stringResource(R.string.no_images_found)
            )
        }
    }
}

