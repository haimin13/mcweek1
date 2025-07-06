package com.example.myapplication1.ui.screens

import com.example.myapplication1.ui.components.gallery.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.components.customLazy.CustomLazyRow


@Composable
fun HomeTabMain(

) {
    val favoriteFriendsIds = listOf("lil monkey", "jaedungg", "haimin13", "bot1", "bot2")

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
    }
}