package com.example.myapplication1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication1.ui.components.playlistsTab.PlaylistTabBar
import com.example.myapplication1.ui.remote.PlaylistViewModel
import com.example.myapplication1.ui.screens.playlists.MyPlaylists
import com.example.myapplication1.ui.screens.playlists.LikedPlaylists
import com.example.myapplication1.ui.screens.playlists.Charts
import com.example.myapplication1.ui.screens.playlists.PlaylistDestinations
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue


@Composable
fun PlaylistsTabMain(
    modifier: Modifier = Modifier,
    myId: Int = 1,
    navController: NavController,
    viewModel: PlaylistViewModel = viewModel()
) {
    val myPlaylistsData by viewModel.myPlaylists.observeAsState(emptyList())
    val likedPlaylistsData by viewModel.likedPlaylists.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.loadMyPlaylists(myId) // 테스트할 유저 ID
        viewModel.loadLikedPlaylists(myId) // 테스트할 유저 ID
    }

    val navTabController = rememberNavController()
    PlaylistTabBar(
        navController = navTabController,
        startDestination = PlaylistDestinations.MYPLAYLISTS,
        modifier = modifier.fillMaxSize(),
    ) { destination, innerModifier ->
        when (destination) {
            PlaylistDestinations.MYPLAYLISTS -> MyPlaylists(modifier = innerModifier, navController = navController, playlistsData = myPlaylistsData)
            PlaylistDestinations.LIKEDPLAYLISTS -> LikedPlaylists(modifier = innerModifier, navController = navController, playlistsData = likedPlaylistsData)
            PlaylistDestinations.CHARTS -> Charts(modifier = innerModifier, navController = navController)
        }
    }
}

