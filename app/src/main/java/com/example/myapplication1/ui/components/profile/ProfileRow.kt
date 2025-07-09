package com.example.myapplication1.ui.components.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication1.ui.components.common.FullPagePopup
import com.example.myapplication1.ui.components.list.ArtistList
import com.example.myapplication1.ui.components.list.PlaylistList
import com.example.myapplication1.ui.components.list.SongList
import com.example.myapplication1.ui.components.list.UserList
import com.example.myapplication1.data.model.Artist
import com.example.myapplication1.data.model.Playlist
import com.example.myapplication1.data.model.Song
import com.example.myapplication1.data.model.User
import com.example.myapplication1.ui.components.popup.ArtistDetailPopup
import com.example.myapplication1.ui.components.popup.PlaylistDetailDialog
import com.example.myapplication1.ui.components.popup.SongDetailPopup
import com.example.myapplication1.ui.components.popup.UserProfilePopup
import com.example.myapplication1.ui.components.popup.dummyUser
import com.example.myapplication1.ui.remote.ArtistViewModel

@Composable
fun ProfileRowSong(
    rowName: String = "",
    entryList: List<Song>,
    artistViewModel: ArtistViewModel = viewModel() // ViewModel 접근
) {
    var selectedSong by remember { mutableStateOf<Song?>(null) }
    var showMoreList by remember { mutableStateOf(false) }

    // 1. 모든 곡의 아티스트 ID 모으기
    val allArtistIds = remember(entryList) {
        entryList.flatMap { it.artist }.distinct()
    }

    // 2. API 호출 (중복 호출 방지 ViewModel 내부 구현 필요)
    LaunchedEffect(allArtistIds) {
        artistViewModel.loadArtistsByIdList(allArtistIds)
    }

    // 3. 관찰된 아티스트 리스트
    val artists by artistViewModel.artists.observeAsState(emptyList())

    // 4. ID → Artist 매핑
    val artistMap = remember(artists) {
        artists.associateBy { it.id }
    }

    Column {
        Text(text = rowName, fontWeight = FontWeight.Bold)

        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(entryList.take(4)) { item ->
                ProfileEntrySong(
                    song = item,
                    onClick = { selectedSong = item },
                    modifier = Modifier.height(40.dp)
                )
            }
            if (entryList.size > 4) {
                item {
                    ProfileEntryMore(
                        modifier = Modifier.height(40.dp),
                        onClick = { showMoreList = true }
                    )
                }
            }
        }
    }
    selectedSong?.let { song ->
        val artistList = song.artist.mapNotNull { artistMap[it] }
        Dialog(onDismissRequest = { selectedSong = null }) {
            SongDetailPopup(
                song = song,
                artists = artistList,
                onDismiss = { selectedSong = null }
            )
        }
    }

    if (showMoreList) {
        FullPagePopup(
            title = rowName,
            onDismiss = { showMoreList = false }
        ) {
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                SongList(songs = entryList)
            }
        }
    }
}

@Composable
fun ProfileRowPlaylist(
    rowName: String = "",
    entryList: List<Playlist>,
) {
    var selectedPlaylist by remember { mutableStateOf<Playlist?>(null) }
    var showMoreList by remember { mutableStateOf(false) }

    selectedPlaylist?.let {
        PlaylistDetailDialog(
            playlist = it,
            onDismiss = { selectedPlaylist = null }
        )
    }

    Column {
        Text(
            text = rowName,
            fontWeight = FontWeight.Bold
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(entryList.take(4)) { item ->
                ProfileEntry(
                    title = item.title,
                    modifier = Modifier.height(24.dp),
                    onClick = { selectedPlaylist = item }
                )
            }
            if (entryList.size > 4) {
                item {
                    ProfileEntryMore(
                        modifier = Modifier.height(24.dp),
                        onClick = { showMoreList = true }
                    )
                }
            }
        }
    }
    if (showMoreList) {
        FullPagePopup (
            title = rowName,
            onDismiss = { showMoreList = false }
        ) {
            Column (
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                PlaylistList(playlists = entryList)
            }
        }
    }
}
@Composable
fun ProfileRowArtist(
    rowName: String = "",
    entryList: List<Artist>,
) {
    var selectedArtist by remember { mutableStateOf<Artist?>(null) }
    var showMoreList by remember { mutableStateOf(false) }

    Column {
        Text(
            text = rowName,
            fontWeight = FontWeight.Bold
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(entryList.take(4)) { item ->
                ProfileEntry(
                    title = item.nickname,
                    onClick = { selectedArtist = item },
                    modifier = Modifier.height(24.dp)
                )
            }
            if (entryList.size > 4) {
                item {
                    ProfileEntryMore(
                        modifier = Modifier.height(24.dp),
                        onClick = { showMoreList = true }
                    )
                }
            }
        }
    }
    selectedArtist?.let { artist ->
        Dialog(onDismissRequest = { selectedArtist = null }) {
            ArtistDetailPopup(artist = artist, onDismiss = { selectedArtist = null })
        }
    }
    if (showMoreList) {
        FullPagePopup (
            title = rowName,
            onDismiss = { showMoreList = false }
        ) {
            Column (
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                ArtistList(artists = entryList)
            }
        }
    }
}

@Composable
fun ProfileRowFriend(
    rowName: String = "",
    entryList: List<User>, //Todo: List<User>로 변경
) {
    var selectedUser by remember { mutableStateOf<User?>(null) }
    var showMoreList by remember { mutableStateOf(false) }

    Column {
        Text(
            text = rowName,
            fontWeight = FontWeight.Bold
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            items(entryList.take(4)) { item ->
                ProfileEntry(
                    title = item.nickname,
                    onClick = { selectedUser = item },
                    modifier = Modifier.height(24.dp)
                )
            }
            if (entryList.size > 4) {
                item {
                    ProfileEntryMore(
                        modifier = Modifier.height(24.dp),
                        onClick = { showMoreList = true }
                    )
                }
            }
        }
    }
    selectedUser?.let { user ->
        Dialog(onDismissRequest = { selectedUser = null }) {
            UserProfilePopup(dummyUser, onDismiss = { selectedUser = null })
        }
    }
    if (showMoreList) {
        FullPagePopup (
            title = rowName,
            onDismiss = { showMoreList = false }
        ) {
            Column (
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                UserList(users = entryList)
            }
        }
    }
}
