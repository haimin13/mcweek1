package com.example.myapplication1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.components.gallery.GalleryEntry
import com.example.myapplication1.ui.components.list.FriendsUpdateList

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

    var notifications = listOf(
        listOf("user0", "song0"),listOf("user1", "song1"),listOf("user2", "song2"),
        listOf("user3", "song3"),listOf("user4", "song4"),listOf("user5", "song5")
    )
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
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
                    GalleryEntry(
                        contentName = id,
                        onTap = {},
                        onLongPress = {}
                        )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
        ) {
            Text(
                text = "Friends' updates",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                FriendsUpdateList(userLogs = dummyUserLogs)
            }
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "Trending now",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
    }
}