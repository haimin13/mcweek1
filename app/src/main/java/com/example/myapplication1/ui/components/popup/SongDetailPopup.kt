package com.example.myapplication1.ui.components.popup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication1.R
import com.example.myapplication1.data.model.Artist
import com.example.myapplication1.ui.components.common.PopupLayout
import com.example.myapplication1.ui.components.list.TagList
//import com.example.myapplication1.ui.components.list.dummyArtistList
import com.example.myapplication1.ui.components.profile.ProfileRowArtist
import com.example.myapplication1.ui.components.profile.ProfileRowFriend
import com.example.myapplication1.ui.components.profile.ProfileRowPlaylist
import com.example.myapplication1.ui.components.profile.dummyUserList
import com.example.myapplication1.ui.screens.playlists.playList
import com.example.myapplication1.ui.screens.tempIdList
import com.example.myapplication1.data.model.Song
import com.example.myapplication1.ui.remote.ArtistViewModel

@Composable
fun SongDetailPopup(
    song: Song,
    artists: List<Artist>,
    onDismiss: () -> Unit
) {
    // 리소스 이미지 불러오기
    val context = LocalContext.current
    val resId = remember(song.thumbnailId) {
        val resourceName = "song_${song.thumbnailId}"
        context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }
    val painter = if (resId != 0) resId else R.drawable.song_dummy

    PopupLayout (
        title = song.title,
//        thumbnailResId = song.thumbnailId?: R.drawable.song_dummy,
        thumbnailResId = painter,
        onDismiss = onDismiss
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProfileRowArtist(
                rowName = "Artist",
                entryList = artists, //song.artist로 바꿔야 함
            )
            TagList(
                title = "Genre",
                tags = listOf(1,2),
                fromMy = false,
                fontSize = 10
            )
            // TODO: 이 노래를 좋아하는 유저 중 친구 리스트
            ProfileRowFriend(
                rowName = "Friends like this",
                entryList = dummyUserList
            )
            ProfileRowPlaylist(
                rowName = "Related playlists",
                entryList = playList
            )
        }
    }
}