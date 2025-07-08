package com.example.myapplication1.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.components.models.Playlist
import com.example.myapplication1.ui.components.models.Song

@Composable
fun ProfileEntrySong(
    song: Song,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .clickable { onClick?.invoke() }
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = song.title,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            color = Color.Black,
        )
        Text(
            text = song.artist.joinToString(", "),
            fontSize = 10.sp,
            lineHeight = 10.sp,
            color = Color.LightGray
        )
    }
}

@Composable
fun ProfileEntry(
    title: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .clickable { onClick?.invoke() }
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            color = Color.Black,
        )
    }
}
@Composable
fun ProfileEntryMore(
    modifier: Modifier,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClick?.invoke() }
            .background(Color.White)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "more...",
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}