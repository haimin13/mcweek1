package com.example.myapplication1.ui.components.popup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.common.PopupLayout
import com.example.myapplication1.ui.components.list.TagList
//import com.example.myapplication1.ui.components.list.dummyArtistList
import com.example.myapplication1.ui.components.profile.ProfileRowArtist
import com.example.myapplication1.ui.components.profile.ProfileRowPlaylist
import com.example.myapplication1.ui.components.profile.ProfileRowSong
import com.example.myapplication1.ui.screens.dummyUserLogs
import com.example.myapplication1.ui.screens.playlists.FriendsFavorites
import com.example.myapplication1.ui.screens.playlists.playList

@Composable
fun UserProfilePopup(
    userId: String,
    onDismiss: () -> Unit
) {
    PopupLayout(
        title = "$userId's music profile",
        thumbnailResId = R.drawable.dummy,
        onDismiss = onDismiss
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TagList(
                title = "Music Preference",
                tags = listOf(1,2,3,4,5,6,7),
                fromMy = false,
                fontSize = 10
            )
            ProfileRowSong(
                rowName = "Liked Songs",
                entryList = FriendsFavorites
            )
            ProfileRowPlaylist(
                rowName = "Playlists",
                entryList = playList
            )
            // TODO
//            ProfileRowArtist(
//                rowName = "Favorite Artists",
//                entryList = dummyArtistList,
//            )
        }
    }
}