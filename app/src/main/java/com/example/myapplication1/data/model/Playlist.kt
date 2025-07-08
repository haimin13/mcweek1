package com.example.myapplication1.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Playlist(
    val id: Int,
    val title: String,
    val author: Int,
    val thumbnailId: Int? = null,
    val keywords: List<String>? = null,
    val likedBy: List<Int>? = null,
    val songIds: List<Int>? = null,
    val visibility: Int = 0,
    val createdAt: String,  // 꼭 String으로!
    val ranking: Int? = null
)