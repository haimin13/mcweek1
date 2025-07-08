package org.example.data

import kotlinx.serialization.Serializable

@Serializable
data class Playlist(
    val id: Long,
    val title: String,
    val createdAt: String
)
