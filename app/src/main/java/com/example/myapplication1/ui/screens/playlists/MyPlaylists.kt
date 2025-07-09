package com.example.myapplication1.ui.screens.playlists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication1.R
import com.example.myapplication1.data.model.Playlist
import com.example.myapplication1.data.model.Song
import com.example.myapplication1.ui.components.list.PlaylistList

// 샘플 리스트
val playList = listOf (
    Playlist(id = 1, title = "Playlist 1", author = 1, thumbnailId = 1, createdAt = "2025" )

)

fun findPlaylistById(id: Int): Playlist {
    return playList.first { it.id == id }
}

@Composable
fun MyPlaylists(modifier: Modifier = Modifier, navController: NavController) {
    Box (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        PlaylistList(playlists = playList)
    }
}