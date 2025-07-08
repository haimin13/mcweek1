package com.example.myapplication1.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val userId: Int,
    val nickname: String,
    val thumbnailId: Int?,
    val likedGenres: List<Int>?,
    val likedSongs: List<ProfileSong>?,
    val likedArtists: List<ProfileArtist>?,
    val createdPlaylists: List<ProfilePlaylist>?
)