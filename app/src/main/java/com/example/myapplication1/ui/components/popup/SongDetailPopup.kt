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
import com.example.myapplication1.ui.components.models.Song
import com.example.myapplication1.ui.components.profile.ProfileRow
import com.example.myapplication1.ui.screens.tempIdList

@Composable
fun SongDetailPopup(
    song: Song,
    onDismiss: () -> Unit
) {
    PopupLayout (
        title = song.title,
        thumbnailResId = song.thumbnailResId?: R.drawable.song_dummy,
        onDismiss = onDismiss
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProfileRow(
                rowName = "Artist",
                entryList = tempIdList.take(1),
                entryType = "Artist"
            )
            TagList(
                title = "Genre",
                tags = listOf(1,2),
                fromMy = false,
                fontSize = 10
            )
            ProfileRow(
                rowName = "Related playlists",
                entryList = tempIdList,
                entryType = "Playlist"
            )
        }
    }
}