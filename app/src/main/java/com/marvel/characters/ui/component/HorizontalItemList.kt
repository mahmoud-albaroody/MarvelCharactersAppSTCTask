package com.marvel.characters.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.placeholder
import com.marvel.characters.R
import com.marvel.characters.data.model.ComicsItem
import com.marvel.characters.ui.theme.DefaultBackgroundColor
import com.marvel.characters.ui.theme.PurpleGrey40
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin


@Composable
fun HorizontalItemList(
    items: ArrayList<ComicsItem>,
    text: String,
    onItemClick: (ComicsItem) -> Unit,
) {

    Column(
        modifier =
        Modifier
            .background(Color.Transparent)
            .alpha(0.8f)
    ) {
        Box(Modifier.padding(vertical = 8.dp)) {
            TextRed(text = text)
        }
        LazyRow(
            modifier = Modifier.wrapContentHeight()
        ) {
            items(items) { item ->
                HorizontalItemView(
                    item = item, onItemClick = {
                        onItemClick(it)
                    }
                )
            }
        }

    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HorizontalItemView(
    item: ComicsItem,
    onItemClick: (ComicsItem) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp // Screen height in DP
    val screenWidth = configuration.screenWidthDp // Screen height in DP
    Column(
        modifier = Modifier
            .width((screenWidth * 0.3).dp)
            .padding(4.dp)
            .clickable {
                onItemClick(item)
            }
    ) {

//        GlideImage(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height((screenHeight * 0.20).dp)
//                .background(color = Color.Transparent),
//            model = item.thumbnail?.path.plus(".").plus(item.thumbnail?.extension),
//            contentDescription = null,
//            transition = CrossFade,
//            loading = placeholder(R.drawable.marvel_logo),
//            failure = placeholder(R.drawable.marvel_logo),
//            contentScale = ContentScale.FillBounds,
//        )
        CoilImage(
            modifier = Modifier
                .fillMaxWidth()
                .height((screenHeight * 0.20).dp)
                .background(color = Color.Transparent),
            imageModel = { item.thumbnail?.path.plus(".").plus(item.thumbnail?.extension) },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Character item",
                colorFilter = null,
            ),
            failure = {
                Image(
                    painter = painterResource(id = R.drawable.marvel_logo), 
                    contentDescription = "Placeholder Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            },
            component = rememberImageComponent {
                +CircularRevealPlugin(
                    duration = 800
                )
                +ShimmerPlugin(
                    baseColor = PurpleGrey40,
                    highlightColor = DefaultBackgroundColor
                )
            },
        )
        Text(
            text = item.title.toString(),
            fontSize = 10.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}



