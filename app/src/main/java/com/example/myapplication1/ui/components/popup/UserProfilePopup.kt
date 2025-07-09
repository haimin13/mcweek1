package com.example.myapplication1.ui.components.popup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.common.PopupLayout
import com.example.myapplication1.ui.components.list.TagList
import com.example.myapplication1.data.model.User
//import com.example.myapplication1.ui.components.list.dummyArtistList
import com.example.myapplication1.ui.components.profile.ProfileRowArtist
import com.example.myapplication1.ui.components.profile.ProfileRowPlaylist
import com.example.myapplication1.ui.components.profile.ProfileRowSong
import com.example.myapplication1.ui.screens.dummyUserLogs
import com.example.myapplication1.ui.screens.playlists.FriendsFavorites
import com.example.myapplication1.ui.screens.playlists.playList

val dummyUser =  User(
    id = 1,
    nickname = "user1",
    thumbnailId = 1,
    friends = mutableListOf(2, 3, 4, 5, 6, 7),
    closeFriends = mutableListOf(4, 5),
    likedGenres = mutableListOf(1, 3),
    likedSongs = mutableListOf(101, 102),
    likedPlaylists = mutableListOf(1, 3),
    likedArtists = mutableListOf(1, 3),
    createdPlaylists = mutableListOf(1, 3)
)
@Composable
fun UserProfilePopup(
    user: User,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val resId = remember(user.id) {
        val resourceName = "user_${user.id}"
        context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }
    val painter = if (resId != 0) resId else R.drawable.dummy

    PopupLayout(
        title = "${user.nickname}'s music profile",
        thumbnailResId = painter,
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