package com.example.myapplication1.ui.components.models

import java.time.LocalDateTime

data class LikedWhen(
    val userId: Int,
    val likedAt: LocalDateTime
)