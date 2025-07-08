package com.example.myapplication1.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: Int,
    val nickname: String,
    val thumbnailId: Int? = null,
    val likedGenres: List<Int>? = null,
    val likedBy: List<Int>? = null,
    val songsId: List<Int>? = null
)