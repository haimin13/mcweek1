package com.example.myapplication1.ui.components.models_unused

import androidx.annotation.DrawableRes


data class User(
    @DrawableRes val thumbnailResId: Int? = null,
    val userId: Int,
    val nickName: String,
    val friends: List<Int>,
    val closeFriends: List<Int>,
    val likedTags: List<LikedItem>? = null,
    val likedSongs: List<LikedItem>? = null,
    val likedPlaylists: List<LikedItem>? = null,
    val likedArtists: List<LikedItem>? = null,
    val createdPlaylists: List<LikedItem>? = null,
)