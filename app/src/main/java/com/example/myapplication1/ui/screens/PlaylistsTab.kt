package com.example.myapplication1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication1.ui.components.list.PlaylistEntry
import androidx.navigation.compose.rememberNavController
import com.example.myapplication1.ui.components.playlistsTab.PlaylistTabBar
import com.example.myapplication1.ui.remote.PlaylistViewModel
import com.example.myapplication1.ui.screens.playlists.MyPlaylists
import com.example.myapplication1.ui.screens.playlists.LikedPlaylists
import com.example.myapplication1.ui.screens.playlists.Charts
import com.example.myapplication1.ui.screens.playlists.PlaylistDestinations
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue


//@Composable
//fun PlaylistsTabMain(
//    modifier: Modifier = Modifier,
//    navController: NavController,
//    viewModel: PlaylistViewModel = viewModel()
//) {
//    val viewModel: PlaylistViewModel = viewModel()
//    val playlists by viewModel.playlists.observeAsState(emptyList())
//
//    LaunchedEffect(Unit) {
//        viewModel.loadPlaylists(userId = 1) // 테스트할 유저 ID
//    }
//
//    val navTabController = rememberNavController()
//    PlaylistTabBar(
//        navController = navTabController,
//        startDestination = PlaylistDestinations.MYPLAYLISTS,
//        modifier = modifier.fillMaxSize()
//    ) { destination, innerModifier ->
//        when (destination) {
//            PlaylistDestinations.MYPLAYLISTS -> MyPlaylists(modifier = innerModifier, navController = navController)
//            PlaylistDestinations.LIKEDPLAYLISTS -> LikedPlaylists(modifier = innerModifier, navController = navController)
//            PlaylistDestinations.CHARTS -> Charts(modifier = innerModifier, navController = navController)
//        }
//    }
//}

@Composable
fun PlaylistsTabMain(modifier: Modifier = Modifier, navController: NavController, viewModel: PlaylistViewModel = viewModel()) {
    val playlists by viewModel.playlists.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.loadPlaylists(userId = 1) // 테스트할 유저 ID
    }

    Column {
        Text("플레이리스트 목록", style = MaterialTheme.typography.headlineSmall)
        LazyColumn {
            items(playlists) { playlist ->
                Text("🎵 ${playlist.title}")
            }
        }
    }
}
