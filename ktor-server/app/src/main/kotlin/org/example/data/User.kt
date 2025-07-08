package org.example.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val nickname: String,
    val thumbnaliId: Int? = null,
    val friends: List<Int>? = null,
    val closeFriends: List<Int>? = null,
    val likedGenres: List<Int>? = null,
    val likedSongs: List<Int>? = null,
    val likedPlaylists: List<Int>? = null,
    val likedArtists: List<Int>? = null,
    val createdPlaylists: List<Int>? = null,
)

