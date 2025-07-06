package com.example.myapplication1.ui.screens.playlists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.list.GenericList
import com.example.myapplication1.ui.components.list.MyPlaylistEntry
import com.example.myapplication1.ui.components.models.Playlist
import com.example.myapplication1.ui.components.models.Song
import androidx.compose.foundation.lazy.items

// 샘플 리스트
val playList = listOf(
    Playlist(
        thumbnailResId = R.drawable.playlist_1,
        id = 1,
        title = "Chill Vibes",
        author = "Luna Ray",
        keywords = listOf("chill", "lofi", "focus"),
        visibility = 0, // public
        isLiked = true,
        songs = listOf(
            Song(
                id = 1,
                title = "Midnight Breeze",
                artist = listOf("Luna Ray"),
                length = "3:45",
                genres = listOf("Chill", "Lofi"),
                isLiked = true,
                ranking = 1,
                thumbnailResId = R.drawable.dummy
            )
        ),
        ranking = 1
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 2,
        title = "Workout Mix",
        author = "DJ Thunder",
        keywords = listOf("energy", "fitness"),
        visibility = 1, // secret
        isLiked = false,
        songs = listOf(
            Song(
                id = 2,
                title = "Electric Punch",
                artist = listOf("DJ Thunder"),
                length = "4:20",
                genres = listOf("Electronic", "EDM"),
                isLiked = false,
                thumbnailResId = R.drawable.dummy
            ),
            Song(
                id = 3,
                title = "High Voltage",
                artist = listOf("PowerUp"),
                length = "3:50",
                genres = listOf("EDM"),
                isLiked = true,
                thumbnailResId = R.drawable.dummy
            )
        ),
        ranking = 2
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 3,
        title = "Acoustic Mornings",
        author = "Emma Stone",
        keywords = listOf("acoustic", "calm", "morning"),
        visibility = 2, // closed
        isLiked = true,
        songs = null, // 없음
        ranking = 3
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 3,
        title = "Acoustic Mornings",
        author = "Emma Stone",
        keywords = listOf("acoustic", "calm", "morning"),
        visibility = 2, // closed
        isLiked = true,
        songs = null, // 없음
        ranking = 3
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 3,
        title = "Acoustic Mornings",
        author = "Emma Stone",
        keywords = listOf("acoustic", "calm", "morning"),
        visibility = 2, // closed
        isLiked = true,
        songs = null, // 없음
        ranking = 3
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 3,
        title = "Acoustic Mornings",
        author = "Emma Stone",
        keywords = listOf("acoustic", "calm", "morning"),
        visibility = 2, // closed
        isLiked = true,
        songs = null, // 없음
        ranking = 3
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 3,
        title = "Acoustic Mornings",
        author = "Emma Stone",
        keywords = listOf("acoustic", "calm", "morning"),
        visibility = 2, // closed
        isLiked = true,
        songs = null, // 없음
        ranking = 3
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 3,
        title = "Acoustic Mornings",
        author = "Emma Stone",
        keywords = listOf("acoustic", "calm", "morning"),
        visibility = 2, // closed
        isLiked = true,
        songs = null, // 없음
        ranking = 3
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 3,
        title = "Acoustic Mornings",
        author = "Emma Stone",
        keywords = listOf("acoustic", "calm", "morning"),
        visibility = 2, // closed
        isLiked = true,
        songs = null, // 없음
        ranking = 3
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 3,
        title = "Acoustic Mornings",
        author = "Emma Stone",
        keywords = listOf("acoustic", "calm", "morning"),
        visibility = 2, // closed
        isLiked = true,
        songs = null, // 없음
        ranking = 3
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 3,
        title = "Acoustic Mornings",
        author = "Emma Stone",
        keywords = listOf("acoustic", "calm", "morning"),
        visibility = 2, // closed
        isLiked = true,
        songs = null, // 없음
        ranking = 3
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 3,
        title = "Acoustic Mornings",
        author = "Emma Stone",
        keywords = listOf("acoustic", "calm", "morning"),
        visibility = 2, // closed
        isLiked = true,
        songs = null, // 없음
        ranking = 3
    )
)

fun findPlaylistById(id: Int): Playlist {
    return playList.first { it.id == id }
}

@Composable
fun MyPlaylists(modifier: Modifier = Modifier, navController: NavController) {
    GenericList(
        modifier = modifier.fillMaxSize(),
        items = playList,
        verticalSpacing = 0.dp,
        onItemClick = { playlist ->
            navController.navigate("playlistDetail/${playlist.id}")
        }
    ) { playlist ->
        MyPlaylistEntry(playlist = playlist, isCharts = false)
    }
//    LazyColumn(
//        modifier = modifier.fillMaxSize(),
//        contentPadding = PaddingValues(vertical = 12.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(playList) { playlist ->
//            MyPlaylistEntry(playlist = playlist, isCharts = false)
//        }
//    }
}