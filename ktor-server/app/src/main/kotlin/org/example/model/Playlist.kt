package org.example.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual
import java.time.LocalDateTime

@Serializable
data class Playlist(
    val id: Int,
    val title: String,
    val author: String,
    val thumbnailId: Int? = null,
    val keywords: List<String>? = null,
    val likedBy: List<Int>? = null,
    val songIds: List<Int>? = null,
    val visibility: Int = 0,
    val createdAt: String,  // 꼭 String으로!
    val ranking: Int? = null
)
