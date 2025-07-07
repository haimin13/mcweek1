package com.example.myapplication1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication1.ui.components.gallery.GalleryEntry
import com.example.myapplication1.ui.components.list.FriendsUpdateList
import com.example.myapplication1.ui.components.list.PlaylistList
import com.example.myapplication1.ui.components.list.SongList
import com.example.myapplication1.ui.screens.playlists.TrendingNow
import com.example.myapplication1.ui.screens.playlists.likedPlayList

@Composable
fun Title(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight(600),
    )
}

@Composable
fun SubTitle(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight(500),
        color = Color(0xFF303F9F)
    )
}

@Composable
fun HomeTabMain(modifier: Modifier = Modifier, navController: NavController) {
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
            .padding(vertical = 20.dp, horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ){
        // recent
        Column(
            modifier = Modifier
                .fillMaxWidth(),
//                .fillMaxHeight(0.4f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Title("Recent")
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

        // Friends' updates
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Title("Friends' updates")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color.LightGray)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                FriendsUpdateList(userLogs = dummyUserLogs)
            }
        }

        // Trending now
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            var selectedType by remember { mutableIntStateOf(0) }

            val types = listOf("Songs", "Playlists")

            Title("Trending now")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = Color.LightGray)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                SubTitle(types[selectedType])

                when (selectedType) {
                    0 -> SongList(songs = TrendingNow.take(3), isCharts = true)
                    1 -> PlaylistList(playlists = likedPlayList.take(3), navController = navController)
                }

                // 하단 슬라이드 버튼
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    types.forEachIndexed {index, type ->
                        val isSelected = index == selectedType
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) Color.White else Color.Gray)
                                .size(8.dp)
                                .clickable { selectedType = index }
                        )
                    }
                }
            }
        }
    }
}