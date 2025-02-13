package com.marvel.characters.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.marvel.characters.data.model.ComicsItem
import com.marvel.characters.ui.theme.DefaultBackgroundColor


@Composable
fun HorizontalItemList(
    items: ArrayList<ComicsItem>,
    text:String,
    onItemClick: (ComicsItem) -> Unit,
) {
    Column(modifier = Modifier.background(DefaultBackgroundColor)) {


        TextRed(text = text)
        LazyRow(
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { item ->
                HorizontalItemView(
                    item = item, onItemClick = {
                        // onItemClick(it)
                    }
                )
            }
        }
    }
}


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
            .height((screenHeight * 0.25).dp)
            .clickable {
                onItemClick(item)
            }
    ) {

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


        Text(
            modifier = Modifier.padding(8.dp),
            text = item.name.toString(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}



