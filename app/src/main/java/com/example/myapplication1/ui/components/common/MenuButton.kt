package com.example.myapplication1.ui.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.ui.components.gallery.GalleryLongPress

@Composable
fun MenuButton() {
    var showMenu by remember { mutableStateOf(false) }

    Icon(
        imageVector = Icons.Default.MoreVert,
        contentDescription = "More",
        tint = Color.Gray,
        modifier = Modifier
            .size(20.dp)
            .clickable { showMenu = true }
//                        .combinedClickable(
//                            onClick = {},
//                            onLongClick = { showMenu = true }
//                        )

    )
    if (showMenu) {
        Dialog(onDismissRequest = { showMenu = false }) {
            GalleryLongPress(
                userId = "null",
                onDismiss = { showMenu = false }
            )
        }
    }
}


