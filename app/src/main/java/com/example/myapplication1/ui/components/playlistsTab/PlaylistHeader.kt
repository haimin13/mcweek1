package com.example.myapplication1.ui.components.playlistsTab

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.sp
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.common.KeywordTag
import com.example.myapplication1.ui.components.models.Playlist
import androidx.compose.material3.Divider
import androidx.compose.ui.text.font.FontWeight

@Composable
fun PlaylistHeader(playlist: Playlist) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = playlist.thumbnailResId ?: R.drawable.dummy),
            contentDescription = null,
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = playlist.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight(600),
            textAlign = TextAlign.Center
        )

        Text(
            text = playlist.author,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            playlist.keywords.forEach { keyword ->
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
