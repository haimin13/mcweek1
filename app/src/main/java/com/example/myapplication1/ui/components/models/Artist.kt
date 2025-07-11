package com.example.myapplication1.ui.components.models

import androidx.annotation.DrawableRes

data class Artist(
    @DrawableRes val thumbnailResId: Int? = null,
    val id: Int,
    val title: String,
    val genres: List<String>,
    val isLiked: Boolean,
    val likedSongs: List<Song>,
    val likedBy: List<LikedWhen>? = null
)