package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class ContextMenu(
    val text: String,
    val iconImage: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun ContextMenuList(
    itemId: String,
    menuItems: List<ContextMenu>
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp,
        modifier = Modifier.padding(20.dp)
    ) {
        Column {
            for (i in 0 until menuItems.size - 1) {
                ContextMenuEntry(itemId, menuItems[i])
                Spacer(modifier = Modifier
                    .height(1.dp)
                    .width(250.dp)
                    .background(color = Color.LightGray))
            }
            ContextMenuEntry(itemId, menuItems[menuItems.size - 1])
        }
    }
}