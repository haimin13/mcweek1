package com.example.myapplication1.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
    entryId: Int = 0
) {
    val showSubtitle = (entryType == "Song")
    val tempTitle = "Title"
    val tempSubtitle = "Various Artists"

    Column(
        modifier = Modifier
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(20))
            .background(color = Color.White)

    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = tempTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
            if (showSubtitle) {
                Text(
                    text = tempSubtitle,
                    fontSize = 7.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}
