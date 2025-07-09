package com.example.myapplication1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication1.ui.components.gallery.GalleryEntry
import com.example.myapplication1.ui.components.list.FriendsUpdateList
import com.example.myapplication1.ui.components.list.SlidingList
import com.example.myapplication1.data.model.Playlist
import com.example.myapplication1.ui.components.popup.PlaylistDetailDialog
import com.example.myapplication1.R
import com.example.myapplication1.ui.remote.ChartViewModel
import com.example.myapplication1.ui.remote.FriendLogViewModel
import com.example.myapplication1.ui.remote.PlaylistViewModel

@Composable
fun Title(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight(600),
    )
}

@Composable
fun SubTitle(text: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight(500),
        color = Color(0xFF303F9F)
    )
}

@Composable
fun HomeTabMain(
    modifier: Modifier = Modifier,
    navController: NavController,
    myId: Int = 1,
    viewModel: PlaylistViewModel = viewModel(),
    chartViewModel: ChartViewModel = viewModel(),
    logViewModel: FriendLogViewModel = viewModel()
) {
    val recentPlaylistsData by viewModel.playlists.observeAsState(emptyList())
    val trendingChart by chartViewModel.trendingNow.observeAsState()
    val friendsUpdate by logViewModel.friendLogs.observeAsState(emptyList())

    LaunchedEffect(viewModel) {
        viewModel.loadRecentPlaylists()
    }
    LaunchedEffect(chartViewModel) {
        chartViewModel.loadTrendingNow(myId)
    }
    LaunchedEffect(logViewModel) {
        logViewModel.loadFriendLogs(myId)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ){
        var selectedPlaylist by remember { mutableStateOf<Playlist?>(null) }
        selectedPlaylist?.let {
            PlaylistDetailDialog(
                playlist = it,
                onDismiss = { selectedPlaylist = null }
            )
        }
        // recent
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Title("Recent")
            LazyRow (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // TODO: Recent playlist 불러오기
                items(recentPlaylistsData) { playlist ->
                    GalleryEntry(
                        contentName = playlist.title,
                        thumbnailResId = R.drawable.playlist_1,
                        onTap = { selectedPlaylist = playlist },
                        onLongPress = {},
                        showText = true
                    )
                }
            }
        }

        // Friends' updates
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Title("Friends' updates")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color.White)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                FriendsUpdateList(userLogs = friendsUpdate)
            }
        }

        // Trending now
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            var selectedType by remember { mutableIntStateOf(0) }

            Title("Trending now")
            trendingChart?.let {
                SlidingList(
                    selectedType = selectedType,
                    onTypeChange = { selectedType = it },
                    trendingSongs = it.songs,
                    trendingPlaylists = it.playlists
                )
            }
        }
    }
}