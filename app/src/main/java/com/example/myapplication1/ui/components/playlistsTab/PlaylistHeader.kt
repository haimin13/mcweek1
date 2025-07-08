package com.example.myapplication1.ui.components.playlistsTab

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.common.KeywordTag
import com.example.myapplication1.data.model.Playlist
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.ui.components.popup.UserProfilePopup

@Composable
fun PlaylistHeader(playlist: Playlist) {
    var isLiked by remember { mutableStateOf(false) }
    var showAuthorProfile by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(8.dp))
//                .pointerInput(Unit) {
//                    detectTapGestures(
//                        onTap = { showTapDialog = true },
//                        onLongPress = { showLongPressPopup = true }
//                    )
//                }
        ) {
            Image(
                //TODO:
//                painter = painterResource(id = playlist.thumbnailId ?: R.drawable.dummy),
                painter = painterResource(R.drawable.dummy),
                contentDescription = null,
                modifier = Modifier.aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { isLiked = !isLiked },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(3.dp)
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = if (isLiked) "좋아요 취소"  else "좋아요",
                    tint = if (isLiked) Color.Red else Color.LightGray
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = playlist.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight(600),
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .clickable { showAuthorProfile = true },
            text = playlist.author.toString(), //Todo: playlist.author (Int)로 이름 가져오기,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray
        )
        if (showAuthorProfile) {
            Dialog(
                onDismissRequest = { showAuthorProfile = false }
            ) {
                UserProfilePopup (playlist.author.toString(), //Todo: playlist.author (Int)로 이름 가져오기
                    {showAuthorProfile = false})
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            playlist.keywords?.forEach { keyword ->
                KeywordTag(text = keyword)
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "haimin13님, jaedungg님 외 13명이 좋아합니다.",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
    }
}
