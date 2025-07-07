package com.example.myapplication1.ui.components.list

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication1.ui.components.models.Playlist
import com.example.myapplication1.ui.components.popup.PlaylistDetailDialog

@Composable
fun PlaylistList(
    modifier: Modifier = Modifier,
    playlists: List<Playlist>,
    verticalSpacing: Int = 8,
    navController: NavController,
    isCharts: Boolean = false
) {
    var selectedPlaylist by remember { mutableStateOf<Playlist?>(null) }

    // 클릭 시 Dialog 띄우기
    selectedPlaylist?.let {
        PlaylistDetailDialog(
            playlist = it,
            onDismiss = { selectedPlaylist = null }
        )
    }

    GenericList(
        modifier = modifier,
        items = playlists,
        verticalSpacing = verticalSpacing.dp,
        onItemClick = { playlist ->
            selectedPlaylist = playlist
        }
    ) { item ->
        PlaylistEntry(playlist = item, isCharts = isCharts)
    }
}