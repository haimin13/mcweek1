package com.example.myapplication1.ui.components.models



data class User(
    val userId: Int,
    val nickName: String,
    val profileImage: String,
    val friends: List<Int>,
    val closeFriends: List<Int>,
    val likedTags: List<SavedItem>,
    val likedSongs: List<SavedItem>,
    val likedPlaylists: List<SavedItem>,
    val likedArtists: List<SavedItem>,
    val createdPlaylists: List<SavedItem>,
)