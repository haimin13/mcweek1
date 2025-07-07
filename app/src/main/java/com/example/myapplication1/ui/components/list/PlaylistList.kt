package com.example.myapplication1.ui.components.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication1.ui.components.models.Playlist

@Composable
fun PlaylistList(
    modifier: Modifier = Modifier,
    playlists: List<Playlist>,
    verticalSpacing: Int = 8,
    isCharts: Boolean = false,
    navController: NavController,
    onItemClick: ((Playlist) -> Unit)? = { playlist ->
        navController.navigate("playlistDetail/${playlist.id}")
    },
) {
    GenericList(
        modifier = modifier,
        items = playlists,
        verticalSpacing = verticalSpacing.dp,
        onItemClick = onItemClick
    ) { item ->
        PlaylistEntry(playlist = item, isCharts = isCharts)
    }
}

