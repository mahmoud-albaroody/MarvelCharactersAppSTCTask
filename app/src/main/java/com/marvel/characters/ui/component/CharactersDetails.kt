package com.marvel.characters.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.marvel.characters.data.model.CharacterItem
import com.marvel.characters.data.model.Thumbnail

@Composable
fun CharacterDetails(characterItem: CharacterItem) {
    Column(Modifier.fillMaxSize()) {
        characterItem.thumbnail?.let { CharacterDetailsImage(it) }
    }
}

@Composable
fun CharacterDetailsImage(thumbnail: Thumbnail) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp // Screen height in DP
    Image(
        painter = rememberAsyncImagePainter(
            thumbnail.path.plus(".").plus(thumbnail.extension)
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxHeight()
            .height((screenHeight * 0.5).dp)
            .clip(RectangleShape)
            .clickable {
//                navController.navigate(Screen.CharacterDetails.route)
            }
    )
}