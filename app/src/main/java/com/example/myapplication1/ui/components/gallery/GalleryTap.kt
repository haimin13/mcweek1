package com.example.myapplication1.ui.components.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.profile.ProfileRow
import com.example.myapplication1.ui.screens.tempIdList

@Composable
fun GalleryTap(
    userId: String,
    onDismiss: () -> Unit
){
    Surface(
        modifier = Modifier.size(400.dp, 550.dp),
        color = Color(0xfff3f3f3),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp
    ) {
        Box(
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onDismiss
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(vertical = 35.dp, horizontal = 30.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userId + "'s music profile",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                GalleryEntry(
                    imageSize = 150
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Music Preference",
                        fontWeight = FontWeight.Bold
                    )
                    // CustomLazyRow(장르)
                    Spacer(modifier = Modifier.height(25.dp)) // 임시
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
    }
}