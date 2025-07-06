package com.example.myapplication1.ui.screens

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


@Composable
fun PlaylistsTabMain(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    PlaylistTabBar(
        navController = navController,
        startDestination = PlaylistDestinations.MYPLAYLISTS,
        modifier = modifier
    ) { destination ->
        when (destination) {
            PlaylistDestinations.MYPLAYLISTS -> MyPlaylists()
            PlaylistDestinations.LIKEDPLAYLISTS -> LikedPlaylists()
            PlaylistDestinations.CHARTS -> Charts()
        }
    }
}
