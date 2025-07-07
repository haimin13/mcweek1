package com.example.myapplication1.ui.components.models

import androidx.annotation.DrawableRes


data class User(
    @DrawableRes val thumbnailResId: Int? = null,
    val userId: Int,
    val nickName: String,
    val friends: List<Int>,
    val closeFriends: List<Int>,
    val likedTags: List<LikedItem>,
    val likedSongs: List<LikedItem>,
    val likedPlaylists: List<LikedItem>,
    val likedArtists: List<LikedItem>,
    val createdPlaylists: List<LikedItem>,
)