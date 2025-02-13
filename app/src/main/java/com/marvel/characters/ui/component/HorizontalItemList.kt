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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.marvel.characters.data.model.ComicsItem
import com.marvel.characters.ui.theme.DefaultBackgroundColor
import com.marvel.characters.ui.theme.SecondaryFontColor


@Composable
fun HorizontalItemList(
    items: ArrayList<ComicsItem>,
    text: String,
    onItemClick: (ComicsItem) -> Unit,
) {
    Column(modifier = Modifier.background(SecondaryFontColor)) {
        Box(Modifier.padding(vertical = 8.dp)) {
            TextRed(text = text)
        }
        LazyRow(
            modifier = Modifier.wrapContentHeight()
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
            .padding(4.dp)

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
                .fillMaxWidth()
                .height((screenHeight * 0.20).dp)
                .clip(RectangleShape)
        )


        Text(
            text = item.title.toString(),
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}



