package com.example.myapplication1.ui.components.models_unused

import androidx.annotation.DrawableRes

data class Song(
    @DrawableRes val thumbnailResId: Int? = null,
    val id: Int,
    val title: String,
    val artist: List<String>, //TODO: List<Artist>로 바꿔야 함
    val length: String,
    val genres: List<String>,
    val isLiked: Boolean,
    val likedBy: List<LikedWhen>? = null,
    val ranking: Int? = null
)