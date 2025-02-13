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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.marvel.characters.data.model.CharacterItem
import com.marvel.characters.data.model.Thumbnail
import com.marvel.characters.ui.theme.DefaultBackgroundColor
import com.marvel.characters.ui.theme.SecondaryFontColor


@Composable
fun CharacterItemList(
    characterList: ArrayList<CharacterItem>? = null,
    onItemClick: (CharacterItem) -> Unit,
) {
    Column(modifier = Modifier.background(DefaultBackgroundColor)) {
        characterList?.let {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(characterList) { item ->
                    CharacterItemView(
                        item = item, onItemClick = {
                            onItemClick(it)
                        }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItemView(
    item: CharacterItem,
    onItemClick: (CharacterItem) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWeight = configuration.screenWidthDp // Screen height in DP

    val screenHeight = configuration.screenHeightDp // Screen height in DP
    // Gradient Background

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height((screenHeight * 0.24).dp)
            .clickable {
                onItemClick(item)
            }
    ) {
        Box {
            Box(
                Modifier
                    .background(SecondaryFontColor)
                    .alpha(0.8f)
            ) {
//        GlideImage(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(color = Color.Transparent),
//            model = item.thumbnail?.path.plus(".").plus(item.thumbnail?.extension) ,
//            contentDescription = null,
//            transition =  CrossFade,
//            contentScale = ContentScale.FillBounds,
//        )
                Image(
                    painter = rememberAsyncImagePainter(
                        item.thumbnail?.path.plus(".").plus(item.thumbnail?.extension)
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RectangleShape)
                )
            }
            // Custom Shape for Label
            val labelShape = GenericShape { size, _ ->
                moveTo(size.width * 0.08f, 0f)
                lineTo(size.width, 0f)
                lineTo(size.width * 0.91f, size.height)
                lineTo(0.0f, size.height)
                lineTo(0.0f, size.height)
            }

            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 38.dp
                    )
                    .align(Alignment.BottomStart)
            ) {

                Box(
                    modifier = Modifier
                        .width((screenWeight * 0.5).dp)
                        .clip(labelShape)
                        .background(Color.White)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = item.name.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


//        CoilImage(
//            modifier = Modifier
//                .fillMaxSize()
//                .clickable {
//                    // navController.navigate(Screen.MovieDetail.route.plus("/${item.id}"))
//                },
//            imageModel = { item.thumbnail?.path.plus(".").plus(item.thumbnail?.extension) },
//            imageOptions = ImageOptions(
//                contentScale = ContentScale.Crop,
//                alignment = Alignment.Center,
//                contentDescription = "Character item",
//                colorFilter = null,
//            ),
//            component = rememberImageComponent {
//                +CircularRevealPlugin(
//                    duration = 1000
//                )
////                +ShimmerPlugin(
////                    baseColor = SecondaryFontColor,
////                    highlightColor = DefaultBackgroundColor
////                )
//            },
//        )
