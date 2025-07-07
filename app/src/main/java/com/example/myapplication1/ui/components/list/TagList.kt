package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.components.common.KeywordTag
import com.example.myapplication1.ui.components.common.RoundTag


@Composable
fun TagList(
    tags: List<Int>,
    fromMy: Boolean = true,
    fontSize: Int = 15
) {
    val musicGenres = listOf(
        "R&B",
        "Hiphop",
        "Pop",
        "Rock",
        "Country",
        "Indie",
        "Idol",
        "Electronica",
        "Jazz",
        "Classics",
        "Blues",
        "Punk",
        "Reggae",
        "Latin",
        "Folk",
        "Trot",
        "Acoustic",
        "Ballad",
        "Crossover",
        "+ Create Your Own"
    )

    @OptIn(ExperimentalLayoutApi::class)
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tags.forEach { tag ->
            RoundTag(
                text = musicGenres[tag],
                fontSize = fontSize,
                isSelected = true,
                onClick = { }
            )
        }
        if (fromMy) {
            RoundTag(
                text = "+ Create Your Own",
                fontSize = fontSize,
                isSelected = false,
                onClick = { }
            )
        }
    }
}

