package com.example.myapplication1.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.R

@Composable
fun KeywordTag(text: String) {
    Text(
        text = "#$text",
        fontSize = 10.sp,
        lineHeight = 16.sp,
        color = Color(0xFF303F9F),
        modifier = Modifier
            .background(Color(0xFFE8EAF6), RoundedCornerShape(6.dp))
            .padding(horizontal = 4.dp, vertical = 0.dp)
    )
}

@Composable
fun RoundTag(
    text: String,
    fontSize: Int = 14,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontWeight = FontWeight(500),
        lineHeight = 16.sp,
        color = if (isSelected) Color.White else Color(0xFF303F9F),
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .clickable { onClick() }
            .then(
                if (isSelected) {
                    Modifier.background(Color(0xFF7986CB))
                } else {
                    Modifier.border(1.dp, Color(0xFF303F9F), RoundedCornerShape(100))
                }
            )
            .padding(horizontal = 10.dp, vertical = 6.dp)
    )
}