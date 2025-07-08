package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication1.ui.screens.SubTitle
import com.example.myapplication1.ui.screens.playlists.TrendingNow
import com.example.myapplication1.ui.screens.playlists.likedPlayList
import com.example.myapplication1.ui.screens.playlists.playList

@Composable
fun SlidingList(
    selectedType: Int,
    onTypeChange: (Int) -> Unit,
) {
    val types = listOf("Songs", "Playlists")

    Box(
        modifier = Modifier
            .height(330.dp)
            .fillMaxWidth()
            .pointerInput(selectedType) {
                detectHorizontalDragGestures { _, dragAmount ->
                    when {
                        (dragAmount > 40 && selectedType > 0) -> {
                            onTypeChange(selectedType - 1)
                            println("dragged left")
                            println(dragAmount)
                            println(selectedType)
                        }
                        (dragAmount < -40 && selectedType < types.lastIndex) -> {
                            onTypeChange(selectedType + 1)
                            println("dragged right")
                            println(dragAmount)
                            println(selectedType)
                        }
                    }
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.White)
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                SubTitle(types[selectedType])

                when (selectedType) {
                    0 -> SongList(
                        songs = TrendingNow.take(4),
                        verticalSpacing = 0,
                        isCharts = true
                    )
                    1 -> PlaylistList(
                        playlists = playList.take(3),
                        verticalSpacing = 8,
                        isCharts = true,
                    )
                }
            }
            // 하단 슬라이드 버튼
            Row(
                modifier = Modifier
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                types.forEachIndexed { index, _ ->
                    val isSelected = index == selectedType
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Color.LightGray else Color.Gray)
                            .size(8.dp)
                            .clickable { onTypeChange(index) }
                    )
                }
            }
        }
    }
}