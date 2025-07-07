package com.example.myapplication1.ui.components.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.components.models.Song

@Composable
fun SongList(modifier: Modifier = Modifier, songs: List<Song>, isCharts: Boolean) {
    GenericList(
        modifier = modifier,
        items = songs,
//        verticalSpacing = 0.dp // 👈 원하는 간격 설정
    ) { song ->
        SongEntry(song = song, isCharts = isCharts)
    }
}
