package com.example.myapplication1.ui.components.models_unused

import androidx.compose.ui.graphics.vector.ImageVector

data class ContextMenu(
    val text: String,
    val iconImage: ImageVector,
    val onClick: () -> Unit
)