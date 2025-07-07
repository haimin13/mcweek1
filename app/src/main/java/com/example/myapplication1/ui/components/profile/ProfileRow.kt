package com.example.myapplication1.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.myapplication1.ui.components.models.Playlist
import com.example.myapplication1.ui.components.models.Song
import com.example.myapplication1.ui.components.popup.SongDetailPopup
import com.example.myapplication1.ui.screens.playlists.FriendsFavorites

@Composable
fun ProfileRowSong(
    rowName: String = "",
    entryList: List<Song>,
    onItemClick: ((Song) -> Unit)? = null,
) {
    var selectedSong by remember { mutableStateOf<Song?>(null) }

    Column {
        Text(
            text = rowName,
            fontWeight = FontWeight.Bold
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(entryList) { item ->
                ProfileEntrySong(
                    song = item,
                    onClick = {
                        selectedSong = item
//                        onItemClick?.invoke(item)
                    }
                )
            }
            item {
                ProfileEntryMore()
            }
        }
    }
    selectedSong?.let { song ->
        Dialog(onDismissRequest = { selectedSong = null }) {
            SongDetailPopup(song = song, onDismiss = { selectedSong = null })
        }
    }
}
@Composable
fun ProfileRowPlaylist(
    rowName: String = "",
    entryList: List<Playlist>,
//    navController: NavController,
    onItemClick: ((Song) -> Unit)? = null
//    onItemClick: ((Playlist) -> Unit)? = { playlist ->
//        navController.navigate("playlistDetail/${playlist.id}")
//    },
) {
    Column {
        Text(
            text = rowName,
            fontWeight = FontWeight.Bold
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(entryList) { item ->
                ProfileEntryPlaylist(
                    playlist = item,
//                    onClick = { onItemClick?.invoke(item) }
                )
            }
            item {
                ProfileEntryMore()
            }
        }
    }
}
//
//
//@Composable
//fun <T> ProfileRow(
//    rowName: String = "",
//    entryList: List<T>,
//    modifier: Modifier = Modifier
//) {
//    Column {
//        Text(
//            text = rowName,
//            fontWeight = FontWeight.Bold
//        )
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(6.dp)
//        ) {
//            items(entryList) { item ->
//                ProfileEntry(
//                    entry = item,
//                    onClick = {
//                        when (item) {
//                            is Song -> {
//                                // Song 클릭 시 로직
//                                println("Clicked Song: ${item.title}")
//                            }
//                            is Playlist -> {
//                                println("Clicked Playlist: ${item.title}")
//                            }
////                            is User -> {
////                                println("Clicked User: ${item.name}")
////                            }
//                            else -> {
//                                println("Unknown type clicked.")
//                            }
//                        }
//                    }
//                )
//            }
//            item {
//                ProfileEntry(
//                    entry = null,
//                    isMore = true
//                )
//            }
//        }
//    }
//}
