package com.example.myapplication1.ui.components.models

import androidx.annotation.DrawableRes

data class Song(
    @DrawableRes val thumbnailResId: Int? = null,
    val id: Int,
    val title: String,
    val artist: List<String>,
    val length: String,
    val genres: List<String>,
    val isLiked: Boolean,
    val ranking: Int? = null
)