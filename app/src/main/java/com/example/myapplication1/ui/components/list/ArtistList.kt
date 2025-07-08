package com.example.myapplication1.ui.components.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.ui.components.popup.SongDetailPopup
import com.example.myapplication1.ui.components.models.Song

@Composable
fun SongList(
    modifier: Modifier = Modifier,
    songs: List<Song>,
    verticalSpacing: Int = 8,
    isCharts: Boolean = false,
    onItemClick: ((Song) -> Unit)? = null,
) {
    var selectedSong by remember { mutableStateOf<Song?>(null) }

    GenericList(
        modifier = modifier,
        items = songs,
        verticalSpacing = verticalSpacing.dp,
        onItemClick = { song ->
            selectedSong = song
            onItemClick?.invoke(song)
        }
    ) { song ->
        SongEntry(song = song, isCharts = isCharts)
    }

    selectedSong?.let { song ->
        Dialog(onDismissRequest = { selectedSong = null }) {
            SongDetailPopup(song = song, onDismiss = { selectedSong = null })
        }
    }
}
