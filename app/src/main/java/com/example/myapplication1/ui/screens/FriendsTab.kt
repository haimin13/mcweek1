package com.example.myapplication1.ui.screens

import com.example.myapplication1.ui.components.gallery.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.components.customLazy.CustomLazyRow

@Composable
fun CloseFriendsRowEntry(modifier: Modifier = Modifier) {

}

@Composable
fun FriendsTabMain(modifier: Modifier = Modifier) {
    // friendsIds는 내 친구 database에서 불러옴
    val favoriteFriendsIds = listOf("lil monkey", "jaedungg", "haimin13", "bot1", "bot2")
    val friendsIds = listOf(
        "lil monkey",
        "jaedungg",
        "bot4",
        "bot5",
        "haimin13",
        "bot1",
        "bot2",
        "bot3",
        "jaedungg",
        "bot4",
        "bot5",
        "jaedungg",
        "bot4",
        "bot5",
    )

    var showTapPopup by remember { mutableStateOf(false) }
    var showLongPressPopup by remember { mutableStateOf(false) }
    val galleryEntryRef: @Composable (
        String,
        Int,
        Int,
        @Composable (String, () -> Unit) -> Unit,
        @Composable (String, () -> Unit) -> Unit
    ) -> Unit = { p1, p2, p3, p4, p5 -> GalleryEntry(p1, p2, p3, p4, p5) }
    val galleryTapRef: @Composable (String, () -> Unit) -> Unit = { p1, p2 -> GalleryTap(p1, p2) }
    val galleryLongPressRef: @Composable (String, () -> Unit) -> Unit = { p1, p2 -> GalleryLongPress(p1, p2) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "close friends",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            CustomLazyRow(
                itemList = favoriteFriendsIds,
                itemContent = galleryEntryRef,
                tapRef = galleryTapRef,
                longPressRef = galleryLongPressRef
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "all friends",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(friendsIds) { id ->
                    GalleryEntry(
                        userId = id,
                        imageSize = 90,
                        tapDialog = galleryTapRef,
                        longPressPopup = galleryLongPressRef
                    )
                }
                // 친구 추가 버튼 추가
            }
        }
    }
}