package com.example.myapplication1.ui.components.list

import android.graphics.Paint.Align
import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.components.models.ContextMenu


@Composable
fun ContextMenuEntry(
    menuItem: ContextMenu
) {
    val text = menuItem.text
    val iconImage = menuItem.iconImage
    val onClick = menuItem.onClick

    Row (
        modifier = Modifier
            .height(45.dp)
            .width(250.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        )
        Icon(
            imageVector = iconImage,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 10.dp)
        )
    }
}