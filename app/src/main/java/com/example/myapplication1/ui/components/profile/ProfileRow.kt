package com.example.myapplication1.ui.components.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProfileRow(
    rowName: String = "",
    entryList: List<Int>,
    entryType: String = ""
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
        }
    }

}