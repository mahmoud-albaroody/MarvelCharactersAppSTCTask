package com.marvel.characters.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.marvel.characters.R
import com.marvel.characters.data.model.CharacterItem
import com.marvel.characters.data.model.Thumbnail
import com.marvel.characters.navigation.Screen
import com.marvel.characters.ui.theme.DefaultBackgroundColor
import com.marvel.characters.ui.theme.SecondaryFontColor
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin


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
    val screenHeight = configuration.screenHeightDp // Screen height in DP
    // Gradient Background
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF646363), Color(0xFFE3E3E3)) // Dark to Light
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height((screenHeight * 0.25).dp)
            .clickable {
                onItemClick(item)
            }
    ) {
        Box {
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
            // Custom Shape for Label
            val labelShape = GenericShape { size, _ ->
                moveTo(0.2f, 0f)                     // Start at top-left
                lineTo(size.width, 0f)     // Move right
                lineTo(size.width * 0.90f, size.height)        // Diagonal cut
                lineTo(size.width, size.height)         // Bottom-left
                lineTo(size.width * 0.10f, size.height)              // Back to bottom-left corner
            }

            // Name Label with Diagonal Cut
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
                    .clip(labelShape)
                    .background(Color.White)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = item.name.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBrush) // Apply gradient
        )


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
    }
}

@Preview
@Composable
fun Character() {
    val navController = rememberNavController()

    CharacterItemView(
        item = CharacterItem(
            thumbnail = Thumbnail(
                path = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec",
                extension = ".jpg"
            )
        ),
        onItemClick = {

        }
    )
}