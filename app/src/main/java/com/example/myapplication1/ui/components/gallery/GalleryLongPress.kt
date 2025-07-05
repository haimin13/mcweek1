package com.example.myapplication1.ui.components.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GalleryLongPress(
    userId: String,
    onDismiss: () -> Unit) {
    Surface(
        modifier = Modifier.size(220.dp, 140.dp),
        color = Color.White,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            Text("Long press dialog")
        }
    }
}