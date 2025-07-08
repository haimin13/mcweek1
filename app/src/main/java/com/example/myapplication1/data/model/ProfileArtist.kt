package com.example.myapplication1.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileArtist(
    val id: Int,
    val nickname: String
)