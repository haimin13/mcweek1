package com.example.myapplication1.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
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
fun ProfileRow(
    rowName: String = "",
    entryList: List<Int>,
    entryType: String = "",
    modifier: Modifier = Modifier
) {
    Column() {
        Text(
            text = rowName,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
        ) {
            items(7) { idx ->
                ProfileEntry(entryType, idx)
            }
            item {
                ProfileEntry(entryType = entryType, isMore = true)
            }
        }
    }

}