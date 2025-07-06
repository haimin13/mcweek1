package com.example.myapplication1.ui.screens.playlists

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.components.list.MyPlaylistEntry
import androidx.navigation.compose.rememberNavController
import com.example.myapplication1.ui.components.playlistsTab.PlaylistTabBar
import com.example.myapplication1.ui.screens.playlists.MyPlaylists
import com.example.myapplication1.ui.screens.playlists.LikedPlaylists
import com.example.myapplication1.ui.screens.playlists.Charts
import com.example.myapplication1.ui.screens.playlists.PlaylistDestinations

// 데이터 클래스 (임시 위치: 실제로는 model 패키지 분리 추천)
data class Song(
    val title: String,
    val author: String,
    val keywords: List<String>,
    val isPrivate: Boolean
)

// 샘플 리스트
val playList = listOf(
    Song("매일이 주말이라면 좋겠어", "me", listOf("cozy", "peaceful"), true),
    Song("기분 좋은 산책", "me", listOf("calm", "lofi"), false),
    Song("비 오는 날의 재즈", "me", listOf("jazz", "rainy"), false)
)

@Composable
fun MyPlaylists(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(playList) { song ->
                MyPlaylistEntry(song = song)
            }
        }
    }
}