package com.example.myapplication1.ui.components.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.list.TagList
import com.example.myapplication1.ui.components.profile.ProfileRow
import com.example.myapplication1.ui.screens.tempIdList

@Composable
fun GalleryTap(
    userId: String,
    onDismiss: () -> Unit
) {
    Popup(
        title = "$userId's music profile",
        thumbnailResId = R.drawable.dummy,
        onDismiss = onDismiss
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Music Preference",
                fontWeight = FontWeight.Bold
            )
            TagList(
                listOf(1,2,3,4,5,6,7,8,9,10,11,12,13),
                fromMy = false,
                fontSize = 10
            )
            ProfileRow(
                rowName = "Liked Songs",
                entryList = tempIdList,
                entryType = "Song"
            )
            ProfileRow(
                rowName = "Playlists",
                entryList = tempIdList,
                entryType = "Playlist"
            )
            ProfileRow(
                rowName = "Favorite Artists",
                entryList = tempIdList,
                entryType = "Artist"
            )
        }
    }
}