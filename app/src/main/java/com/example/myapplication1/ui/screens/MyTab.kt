package com.example.myapplication1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.gallery.GalleryEntry
import com.example.myapplication1.ui.components.list.TagList
import com.example.myapplication1.ui.components.profile.ProfileRow
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


val tempIdList = listOf(1,2,3,4,5,6,7,8,9)
@Composable
fun MyTabMain(modifier: Modifier = Modifier) {
    var myId by remember { mutableIntStateOf(0) }
    var likedTags = listOf(
        listOf(1,3,5,6,7,10,12,16,15,8,9),
        listOf(2,4,5,7)
    )

    var nicknames = listOf(
        "lil monkey",
        "jaedungg",
        "haimin13"
    )
    Column(
        modifier = Modifier
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "My Profile",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(50.dp))
            GalleryEntry(
                contentName = "",
                showText = false,
                imageSize = 200,
                onTap = {}, onLongPress = {}
            )
            Row(
                modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = nicknames[myId],
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                IconButton(
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                    )
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Music Preference",
                    fontWeight = FontWeight.Bold
                )
                TagList(
                    tags = likedTags[0]
                )
            }
            ProfileRow(
                rowName = "Liked Songs",
                entryList = tempIdList,
                entryType = "Song",
                modifier = modifier
            )
            ProfileRow(
                rowName = "Playlists",
                entryList = tempIdList,
                entryType = "Playlist",
                modifier = modifier
            )
            ProfileRow(
                rowName = "Favorite Artists",
                entryList = tempIdList,
                entryType = "Artist",
                modifier = modifier
            )
        }
    }
}