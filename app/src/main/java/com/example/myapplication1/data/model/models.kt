package com.example.myapplication1.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Playlist(
    val id: Int,
    val title: String,
    val author: Int,
    val thumbnailId: Int? = null,
    @Contextual val keywords: MutableList<String>? = null,
    @Contextual val likedBy: MutableList<Int>? = null,
    @Contextual val songIds: MutableList<Int>? = null,
    val visibility: Int = 0,
    val createdAt: String,  // 꼭 String으로!
    val ranking: Int? = null
)
