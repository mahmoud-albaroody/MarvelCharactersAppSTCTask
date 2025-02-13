package com.marvel.characters.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextWhite(text: String,fontSize:Int) {
    Text(
        text = text,
        style = TextStyle(
            color = Color.White,
            fontSize = fontSize.sp
        )
    )
}