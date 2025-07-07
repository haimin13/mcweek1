package com.example.myapplication1.ui.screens.playlists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication1.ui.components.models.Song
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.common.RoundTag
import com.example.myapplication1.ui.components.list.GenericList
import com.example.myapplication1.ui.components.list.SongEntry


val FriendsFavorites = listOf(
    Song(
        id = 1,
        title = "Drowning",
        artist = listOf("WOODZ"),
        length = "4:06",
        genres = listOf("Chill", "Lofi"),
        isLiked = true,
        ranking = 1,
        thumbnailResId = R.drawable.song_1
    ),
    Song(
        id = 2,
        title = "Happy",
        artist = listOf("Day6", "Day5"),
        length = "3:10",
        genres = listOf("Electronic", "Band"),
        isLiked = false,
        ranking = 2,
        thumbnailResId = R.drawable.song_2
    ),
)

val TrendingNow = listOf(
    Song(
        id = 11,
        title = "Dirty Work",
        artist = listOf("aespa"),
        length = "3:01",
        genres = listOf("Chill", "Hiphop"),
        isLiked = true,
        ranking = 1,
        thumbnailResId = R.drawable.song_3
    ),
    Song(
        id = 12,
        title = "Meow",
        artist = listOf("kitty"),
        length = "3:10",
        genres = listOf("Electronic", "Band"),
        isLiked = false,
        ranking = 2,
        thumbnailResId = R.drawable.dummy
    ),
    Song(
        id = 12,
        title = "MeowMeow",
        artist = listOf("kitty", "puppy"),
        length = "3:43",
        genres = listOf("Electronic", "Band"),
        isLiked = false,
        ranking = 3,
        thumbnailResId = R.drawable.song_dummy
    ),
)

@Composable
fun Charts(modifier: Modifier = Modifier, navController: NavController) {
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

        GenericList(
            modifier = Modifier.fillMaxSize(),
            items = if (selectedTag == "Friends' favorites") FriendsFavorites else TrendingNow,
//            verticalSpacing = 0.dp,
            onItemClick = { playlist ->
                navController.navigate("playlistDetail/${playlist.id}")
            }
        ) { song ->
            SongEntry(song = song, isCharts = true)
        }
    }
}