package com.example.myapplication1.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfilePlaylist(
    val id: Int,
    val title: String
)