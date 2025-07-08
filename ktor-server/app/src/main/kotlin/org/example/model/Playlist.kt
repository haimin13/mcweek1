package org.example.models

import java.time.LocalDateTime

data class Playlist(
    val id: Int,
    val title: String,
    val author: String,
    val thumbnailId: Int? = null,
    val keywords: List<String>? = null,
    val likedBy: List<Int>? = null,
    val songIds: List<Int>? = null,
    val visibility: Int = 0,
    val createdAt: String? = null,
    val ranking: Int? = null
)
