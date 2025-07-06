package com.example.myapplication1.ui.screens.playlists

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.components.list.SongList
import com.example.myapplication1.ui.components.models.Playlist
import com.example.myapplication1.ui.components.playlistsTab.PlaylistHeader

@Composable
fun PlaylistDetailScreen(playlist: Playlist, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        PlaylistHeader(playlist)
        Spacer(modifier = Modifier.height(16.dp))
        SongList(songs = playlist.songs.orEmpty(), isCharts = false)
    }
}