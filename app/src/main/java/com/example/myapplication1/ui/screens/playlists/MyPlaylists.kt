package com.example.myapplication1.ui.screens.playlists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication1.R
import com.example.myapplication1.data.model.Playlist
import com.example.myapplication1.ui.components.list.PlaylistList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

// 샘플 리스트
val playList = listOf(
    Playlist(
        id = 1,
        title = "감성 팝",
        author = 1,
        thumbnailId = 201,
        keywords = mutableListOf("감성", "팝", "밤산책"),
        likedBy = mutableListOf(2, 3),
        songIds = mutableListOf(1, 3, 5),
        visibility = 0,
        createdAt = "2024-06-01T12:00:00",
        ranking = 1
    ),
    Playlist(
        id = 2,
        title = "아침에 듣기 좋은 노래",
        author = 2,
        thumbnailId = 202,
        keywords = mutableListOf("모닝루틴", "활력"),
        likedBy = mutableListOf(1, 3, 4),
        songIds = mutableListOf(2, 4),
        visibility = 0,
        createdAt = "2024-06-02T08:30:00",
        ranking = 2
    )
)

@Composable
fun MyPlaylists(modifier: Modifier = Modifier, navController: NavController, playlistsData: List<Playlist>) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        PlaylistList(playlists = playList, isMy = true)
    }
}