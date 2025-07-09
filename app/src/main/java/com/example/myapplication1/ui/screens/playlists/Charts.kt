package com.example.myapplication1.ui.screens.playlists

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication1.data.model.Song
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.common.RoundTag
import com.example.myapplication1.ui.components.list.GenericList
import com.example.myapplication1.ui.components.list.SongEntry
import com.example.myapplication1.ui.components.list.SongList
import com.example.myapplication1.ui.remote.ChartViewModel
import com.example.myapplication1.ui.remote.PlaylistViewModel
import androidx.compose.runtime.getValue
import com.example.myapplication1.data.model.ChartResponse
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text


val FriendsFavorites = listOf(
    Song(
        id = 1,
        title = "Drowning",
//        artist = "WOODZ",
        artist = listOf(1),
        length = "4:06",
//        genres = listOf("Chill", "Lofi"),
//        isLiked = true,
        ranking = 1,
//        thumbnailResId = R.drawable.song_1
    ),
    Song(
        id = 2,
        title = "Happy",
        artist = listOf(1),
//        artist = "Day6",
        length = "3:10",
//        genres = listOf("Electronic", "Band"),
//        isLiked = false,
        ranking = 2,
//        thumbnailResId = R.drawable.song_2
    ),
)

val TrendingNow = listOf(
    Song(
        id = 11,
        title = "Dirty Work",
        artist = listOf(1),
//        artist = "aespa",
        length = "3:01",
//        genres = listOf("Chill", "Hiphop"),
//        isLiked = true,
//        ranking = 1,
//        thumbnailResId = R.drawable.song_3
    ),
    Song(
        id = 12,
        title = "Meow",
        artist = listOf(1),
//        artist = "kitty",
        length = "3:10",
//        genres = listOf("Electronic", "Band"),
//        isLiked = false,
        ranking = 2,
//        thumbnailResId = R.drawable.dummy
    ),
    Song(
        id = 12,
        title = "MeowMeow",
        artist = listOf(1),
//        artist = "kitty & puppy",
        length = "3:43",
//        genres = listOf("Electronic", "Band"),
//        isLiked = false,
        ranking = 3,
//        thumbnailResId = R.drawable.song_dummy
    ),
    Song(
        id = 12,
        title = "MeowMeow",
        artist = listOf(1),
//        artist = "kitty & puppy",
        length = "3:43",
//        genres = listOf("Electronic", "Band"),
//        isLiked = false,
        ranking = 4,
//        thumbnailResId = R.drawable.song_dummy
    ),
    Song(
        id = 12,
        title = "MeowMeow",
        artist = listOf(1),
//        artist = "kitty & puppy",
        length = "3:43",
//        genres = listOf("Electronic", "Band"),
//        isLiked = false,
        ranking = 5,
//        thumbnailResId = R.drawable.song_dummy
    ),
    Song(
        id = 12,
        title = "MeowMeow",
        artist = listOf(1),
//        artist = " ",
        length = "3:43",
//        genres = listOf("Electronic", "Band"),
//        isLiked = false,
        ranking = 6,
//        thumbnailResId = R.drawable.song_dummy
    ),
)

@Composable
fun Charts(
    modifier: Modifier = Modifier,
    navController: NavController,

    viewModel: ChartViewModel = viewModel(),
    friendsFavorite: ChartResponse?,
    trendingNow: ChartResponse?
) {
//    val friendsFavoriteChart by viewModel.friendsFavorite.observeAsState()
//    val trendingNowChart by viewModel.trendingNow.observeAsState()
//
//    LaunchedEffect(Unit) {
//        viewModel.loadFriendsFavorite(myId) // 테스트할 유저 ID
//        viewModel.loadTrendingNow(myId) // 테스트할 유저 ID
//    }

    var selectedTag by remember { mutableStateOf("Friends' favorites") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RoundTag(
                text = "Friends' favorite",
                isSelected = selectedTag == "Friends' favorites",
                onClick = { selectedTag = "Friends' favorites" }
            )
            RoundTag(
                text = "Trending now",
                isSelected = selectedTag == "Trending now",
                onClick = { selectedTag = "Trending now" }
            )
        }

        val selectedSong = if (selectedTag == "Friends' favorites") friendsFavorite?.songs else trendingNow?.songs
        SongList(songs = selectedSong ?: emptyList(), isCharts = true)
    }
}