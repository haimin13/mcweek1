package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.components.Edit.GenreSelectionDialog
import com.example.myapplication1.ui.components.common.KeywordTag
import com.example.myapplication1.ui.components.common.RoundTag


@Composable
fun TagList(
    tags: List<Int>,
    fromMy: Boolean = true,
    fontSize: Int = 15,
    onTagsUpdate: ((List<Int>) -> Unit)? = null
) {
    val musicGenres = listOf(
        "R&B", "Hiphop", "Pop", "Rock", "Country", "Indie", "Idol",
        "Electronica", "Jazz", "Classics", "Blues", "Punk", "Reggae",
        "Latin", "Folk", "Trot", "Acoustic", "Ballad", "Crossover"
    )
    var showGenreDialog by remember { mutableStateOf(false) }

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
                text = "Edit",
                fontSize = fontSize,
                isSelected = false,
                onClick = { showGenreDialog = true }
            )
        }
    }

    GenreSelectionDialog(
        showDialog = showGenreDialog,
        currentSelectedGenres = tags,
        onGenresUpdate = { newGenres ->
            onTagsUpdate?.invoke(newGenres)
        },
        onDismiss = { showGenreDialog = false }
    )
}

