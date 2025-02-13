package com.marvel.characters.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.marvel.characters.data.model.CharacterItem

@Composable
fun DescriptionSection(characterItem:CharacterItem){
    Column {
        CharacterDetails(characterItem = characterItem)
        TextRed(text = "NAME")
        TextWhite(text = characterItem.name?:"")
        TextRed(text = "DESCRIPTION")
        TextWhite(text = characterItem.description?:"")
    }
}