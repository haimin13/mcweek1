package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.ui.components.popup.SongDetailPopup
import com.example.myapplication1.data.model.Song

@Composable
fun SongList(
    modifier: Modifier = Modifier,
    songs: List<Song>,
    verticalSpacing: Int = 8,
    isCharts: Boolean = false,
    onItemClick: ((Song) -> Unit)? = null,
) {
    var selectedSong by remember { mutableStateOf<Song?>(null) }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing.dp)
    ) {
        itemsIndexed(songs) { index, item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = onItemClick != null) {
                        selectedSong = item
                        onItemClick?.invoke(item)
                    }
            ) {
                SongEntry(song = item, isCharts = isCharts, ranking = index + 1)
            }
        }
    }

    selectedSong?.let { song ->
        Dialog(onDismissRequest = { selectedSong = null }) {
            SongDetailPopup(song = song, onDismiss = { selectedSong = null })
        }
    }
}
