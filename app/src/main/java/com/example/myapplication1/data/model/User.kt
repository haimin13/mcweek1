package com.example.myapplication1.data.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    var nickname: String,
    val thumbnailId: Int? = null,
    val friends: List<Int>? = null,
    val closeFriends: List<Int>? = null,
    var likedGenres: List<Int>? = null,
    val likedSongs: List<Int>? = null,
    val likedPlaylists: List<Int>? = null,
    val likedArtists: List<Int>? = null,
    val createdPlaylists: List<Int>? = null,
)