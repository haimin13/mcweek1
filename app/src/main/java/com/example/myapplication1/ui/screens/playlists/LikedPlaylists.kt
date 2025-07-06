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

val playListtt = Playlist(
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
                thumbnailResId = R.drawable.song_1
            ),
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
)

@Composable
fun LikedPlaylists(
    modifier: Modifier = Modifier
) {

}

@Composable
fun Charts(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(songList) { song ->
                SongEntry(song = song, isCharts = true)
            }
        }
    }
    GenericList(
        items = songList,
        verticalSpacing = 0.dp,
//        onItemClick = { playlist ->
//            navController.navigate("playlistDetail/${playlist.id}")
//        }
    ) { song ->
        SongEntry(song = song, isCharts = false)
    }
}

//@Composable
//fun Charts(modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(24.dp) // 위아래 간격
//    ) {
//        Box(
//            modifier = Modifier
//                .weight(1f) // 👈 공간 반 나누기
//                .fillMaxWidth(),
//            contentAlignment = Alignment.TopCenter
//        ) {
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = PaddingValues(vertical = 8.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(songList) { song ->
//                    SongEntry(song = song, isCharts = true)
//                }
//            }
//        }
//
//        Box(
//            modifier = Modifier
//                .weight(1f) // 👈 아래 Box도 공간 반 나누기
//                .fillMaxWidth(),
//            contentAlignment = Alignment.TopCenter
//        ) {
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = PaddingValues(vertical = 8.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(songList) { song ->
//                    SongEntry(song = song, isCharts = false)
//                }
//            }
//        }
//    }
//}