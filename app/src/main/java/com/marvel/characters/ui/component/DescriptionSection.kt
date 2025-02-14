package com.marvel.characters.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.marvel.characters.R
import com.marvel.characters.data.model.CharacterItem

@Composable
fun DescriptionSection(characterItem: CharacterItem) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp // Screen height in DP
    Column(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .height((screenHeight * 0.47).dp)
        ) {
            CharacterDetails(characterItem = characterItem)
        }
        Column(Modifier.padding(horizontal = 8.dp)) {

            Column(Modifier.padding(vertical = 4.dp)) {
                characterItem.name?.let {
                    Box(Modifier.padding(vertical = 4.dp)) {
                        TextRed(text = stringResource(R.string.name))
                    }

                    Box(Modifier.padding(vertical = 4.dp)) {
                        TextWhite(text = characterItem.name, fontSize = 16)
                    }
                }
            }
            characterItem.description?.let {
                Box(Modifier.padding(vertical = 4.dp)) {
                    TextRed(text = stringResource(R.string.description))
                }
                Box(Modifier.padding(vertical = 4.dp)) {
                    TextWhite(text = characterItem.description , fontSize = 16)
                }
            }
        }

    }
}