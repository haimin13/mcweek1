package org.example.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual
import java.time.LocalDateTime

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

@Serializable
data class Song(
    val id: Int,
    val title: String,
    val artist: String,
    val length: String,
    val thumbnailId: Int? = null,
    @Contextual val genres: MutableList<Int>? = null,
    @Contextual val likedBy: MutableList<Int>? = null,
    @Contextual val includedIn: MutableList<Int>? = null,
    val ranking: Int? = null
)