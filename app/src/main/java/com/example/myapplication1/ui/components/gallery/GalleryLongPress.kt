package com.example.myapplication1.ui.components.gallery


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.list.ContextMenuList
import com.example.myapplication1.ui.components.models.ContextMenu

@Composable
fun GalleryLongPress(
    userId: String,
    onDismiss: () -> Unit) {
    val galleryMenuItems = listOf(
        ContextMenu("see profile", Icons.Outlined.AccountCircle, onDismiss),
        //if (!close)
        ContextMenu("add to close friend", Icons.Filled.Favorite, onDismiss),
        //else ContextMenu("remove close friend", Icons.Outlined.Favorite, onDismiss),
        // if (!muted)
        ContextMenu("mute friend",
            ImageVector.vectorResource(R.drawable.outline_notifications_off_24),
            onDismiss),
        // else ContextMenu("unmute friend", Icons.Outlined.Notifications, onDismiss),
        ContextMenu("remove friend", Icons.Outlined.Delete, onDismiss)
    )
    ContextMenuList(itemId = userId, menuItems = galleryMenuItems)
}