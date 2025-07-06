package com.example.myapplication1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.components.gallery.RecentPlaylistsEntry

@Composable
fun HomeTabMain(modifier: Modifier = Modifier) {
    var recentPlaylists = listOf(
        "매일이 주말이라면 좋겠어",
        "슬슬 연말 분위기를 내볼까요?",
        "매일이 주말이라면 좋겠어",
        "슬슬 연말 분위기를 내볼까요?",
        "매일이 주말이라면 좋겠어",
        "슬슬 연말 분위기를 내볼까요?"
    )
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "Recent",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(recentPlaylists) { id ->
                    RecentPlaylistsEntry(id, onClick = {})
                }
            }
        }
    }
}