package com.example.myapplication1.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

@Composable
fun ProfileEntry(
    entryType: String = "",
    entryId: Int = 0,
    userId: Int = 0,
    isMore: Boolean = false
) {
    val showSubtitle = (entryType == "Song")
    val tempTitle = "Title"
    val tempSubtitle = "Various Artists"

    Column(
        modifier = Modifier
            .clickable {  }
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color = Color.White)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = if (!isMore) tempTitle else "more..",
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            color = if (isMore) Color.Gray else Color.Black,
        )
        if (showSubtitle) {
            Text(
                text = if (!isMore) tempSubtitle else "",
                fontSize = 10.sp,
                lineHeight = 10.sp,
                color = Color.LightGray
            )
        }
    }
}
