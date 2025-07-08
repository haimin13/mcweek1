package com.example.myapplication1.ui.components.models

import java.time.LocalDateTime

data class LikedItem (
    val itemId: Int,
    val itemType: String,
    val savedAt: LocalDateTime
)