package com.example.myapplication1.ui.components.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.ui.components.models.Playlist
import com.example.myapplication1.ui.components.models.Song
import com.example.myapplication1.ui.components.popup.PlaylistDetailDialog
import com.example.myapplication1.ui.components.popup.SongDetailPopup

@Composable
fun ProfileRowSong(
    rowName: String = "",
    entryList: List<Song>,
) {
    var selectedSong by remember { mutableStateOf<Song?>(null) }

    Column {
        Text(
            text = rowName,
            fontWeight = FontWeight.Bold
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(entryList) { item ->
                ProfileEntrySong(
                    song = item,
                    onClick = { selectedSong = item },
                    modifier = Modifier.height(40.dp)
                )
            }
            item {
                ProfileEntryMore(
                    modifier = Modifier.height(40.dp)
                )
            }
        }
    }
    selectedSong?.let { song ->
        Dialog(onDismissRequest = { selectedSong = null }) {
            SongDetailPopup(song = song, onDismiss = { selectedSong = null })
        }
    }
}
@Composable
fun ProfileRowPlaylist(
    rowName: String = "",
    entryList: List<Playlist>,
) {
    var selectedPlaylist by remember { mutableStateOf<Playlist?>(null) }

    selectedPlaylist?.let {
        PlaylistDetailDialog(
            playlist = it,
            onDismiss = { selectedPlaylist = null }
        )
    }

    Column {
        Text(
            text = rowName,
            fontWeight = FontWeight.Bold
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(entryList) { item ->
                ProfileEntryPlaylist(
                    playlist = item,
                    modifier = Modifier.height(30.dp),
                    onClick = { selectedPlaylist = item }
                )
            }
            item {
                ProfileEntryMore(modifier = Modifier.height(30.dp))
            }
        }
    }
}