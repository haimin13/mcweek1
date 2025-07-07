package com.example.myapplication1.ui.components.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.components.models.Song

@Composable
fun SongList(
    modifier: Modifier = Modifier,
    songs: List<Song>,
    verticalSpacing: Int = 8,
    isCharts: Boolean = false,
    onItemClick: ((Song) -> Unit)? = null,
) {
    GenericList(
        modifier = modifier,
        items = songs,
        verticalSpacing = verticalSpacing.dp,
        onItemClick = onItemClick
    ) { song ->
        SongEntry(song = song, isCharts = isCharts)
    }
}
