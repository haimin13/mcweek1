package com.example.myapplication1.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeywordTag(text: String) {
    Text(
        text = "#$text",
        fontSize = 10.sp,
        lineHeight = 16.sp,
        color = Color(0xFF5C6BC0),
        modifier = Modifier
            .background(Color(0xFFE8EAF6), RoundedCornerShape(6.dp))
            .padding(horizontal = 4.dp, vertical = 0.dp)
    )
}