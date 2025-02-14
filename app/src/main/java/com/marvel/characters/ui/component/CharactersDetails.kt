package com.marvel.characters.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.marvel.characters.R
import com.marvel.characters.data.model.CharacterItem
import com.marvel.characters.data.model.Thumbnail

@Composable
fun CharacterDetails(characterItem: CharacterItem) {
    Column(Modifier.fillMaxSize()) {
        characterItem.thumbnail?.let { CharacterDetailsImage(it) }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterDetailsImage(thumbnail: Thumbnail) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp // Screen height in DP
//    Image(
//        painter = rememberAsyncImagePainter(
//            thumbnail.path.plus(".").plus(thumbnail.extension)
//        ),
//        contentDescription = null,
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .fillMaxWidth()
//            .height((screenHeight * 0.5).dp)
//            .clip(RectangleShape)
//            .clickable {
//
//            }
//    )
    GlideImage(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        model =  thumbnail.path.plus(".").plus(thumbnail.extension),
        contentDescription = null,
        transition = CrossFade,
        failure = placeholder(R.drawable.marvel_logo),
        contentScale = ContentScale.FillBounds,
    )
}
