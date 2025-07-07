package com.example.myapplication1.ui.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.ui.components.gallery.GalleryLongPress

@Composable
fun LikeButton() {
    var isLiked by remember { mutableStateOf(false) }

    val favoriteIcon = when (isLiked) {
        true -> Icons.Default.Favorite
        false -> Icons.Default.FavoriteBorder
    }
    Icon(
            imageVector = favoriteIcon,
            contentDescription = "Like",
            tint = if (isLiked) Color.Red else Color.Gray,
            modifier = Modifier
                .size(20.dp)
                .clickable { isLiked = !isLiked }
        )
}


