package com.example.myapplication1.data.model

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class UserLikeLog(
    val userId: Int,
    val itemType: String,  // "song" or "playlist"
    val itemId: Int,
    val likedTime: String
)