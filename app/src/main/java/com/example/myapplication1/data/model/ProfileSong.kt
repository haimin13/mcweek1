package com.example.myapplication1.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileSong(
    val id: Int,
    val title: String,
    val artist: String
)