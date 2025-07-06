package com.example.myapplication1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication1.ui.components.list.MyPlaylistEntry
import androidx.navigation.compose.rememberNavController
import com.example.myapplication1.ui.components.playlistsTab.PlaylistTabBar
import com.example.myapplication1.ui.screens.playlists.MyPlaylists
import com.example.myapplication1.ui.screens.playlists.LikedPlaylists
import com.example.myapplication1.ui.screens.playlists.Charts
import com.example.myapplication1.ui.screens.playlists.PlaylistDestinations


@Composable
fun PlaylistsTabMain(modifier: Modifier = Modifier, navController: NavController) {
    val navTabController = rememberNavController()
    PlaylistTabBar(
        navController = navTabController,
        startDestination = PlaylistDestinations.MYPLAYLISTS,
        modifier = modifier.fillMaxSize()
    ) { destination, innerModifier ->
        when (destination) {
            PlaylistDestinations.MYPLAYLISTS -> MyPlaylists(modifier = innerModifier, navController = navController)
            PlaylistDestinations.LIKEDPLAYLISTS -> LikedPlaylists()
            PlaylistDestinations.CHARTS -> Charts()
        }
    }
}
