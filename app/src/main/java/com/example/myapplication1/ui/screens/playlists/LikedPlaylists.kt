package com.example.myapplication1.ui.screens.playlists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication1.ui.components.models.Song
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.list.PlaylistList
import com.example.myapplication1.data.model.Playlist


@Composable
fun LikedPlaylists(
    modifier: Modifier = Modifier,
    navController: NavController,
    playlistsData: List<Playlist>
) {
    Box (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        PlaylistList(playlists = playlistsData)
    }
}
