package com.example.myapplication1.ui.screens.playlists
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication1.ui.components.models.Song
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.list.GenericList
import com.example.myapplication1.ui.components.list.MyPlaylistEntry
import com.example.myapplication1.ui.components.list.SongEntry
import com.example.myapplication1.ui.components.models.Playlist


val songList = listOf(
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

val likedPlayList = listOf (
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
                thumbnailResId = R.drawable.song_1
            ),
            Song(
                id = 1,
                title = "Midnight Breeze",
                artist = listOf("Luna Ray"),
                length = "3:45",
                genres = listOf("Chill", "Lofi"),
                isLiked = true,
            )
        ),
    ),
    Playlist(
        thumbnailResId = R.drawable.dummy,
        id = 2,
        title = "Workout Mix",
        author = "DJ Thunder",
        keywords = listOf("energy", "fitness"),
        visibility = 1, // secret
        isLiked = true,
        songs = listOf(
            Song(
                id = 2,
                title = "Electric Punch",
                artist = listOf("DJ Thunder"),
                length = "4:20",
                genres = listOf("Electronic", "EDM"),
                isLiked = false,
            ),
            Song(
                id = 3,
                title = "High Voltage",
                artist = listOf("PowerUp"),
                length = "3:50",
                genres = listOf("EDM"),
                isLiked = true,
            )
        ),
    )
)


@Composable
fun LikedPlaylists(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    GenericList(
        modifier = modifier.fillMaxSize(),
        items = likedPlayList,
        verticalSpacing = 0.dp,
        onItemClick = { playlist ->
            navController.navigate("playlistDetail/${playlist.id}")
        }
    ) { playlist ->
        MyPlaylistEntry(playlist = playlist, isCharts = false)
    }
}

@Composable
fun Charts(modifier: Modifier = Modifier, navController: NavController) {
    GenericList(
        modifier = modifier.fillMaxSize(),
        items = songList,
        verticalSpacing = 0.dp,
        onItemClick = { playlist ->
            navController.navigate("playlistDetail/${playlist.id}")
        }
    ) { song ->
        SongEntry(song = song, isCharts = true)
    }
}