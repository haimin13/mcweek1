package com.example.myapplication1.ui.components.models_unused

import androidx.annotation.DrawableRes

data class Playlist(
    @DrawableRes val thumbnailResId: Int? = null,
    val id: Int,
    val title: String,
    val author: String,
    val keywords: List<String>,
    val visibility: Int, // 0: public, 1: secret, 2: closed
    val isLiked: Boolean,
    val likedBy: List<LikedWhen>? = null,
    val songs: List<Song>? = null,
    val ranking: Int? = null
)