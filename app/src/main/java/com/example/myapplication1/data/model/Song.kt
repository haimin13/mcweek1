package com.example.myapplication1.data.model // <-- 중요: 앱의 패키지명으로 변경

import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val id: Int,
    val title: String,
    val artist: String,
    val length: String,
    val thumbnailId: Int? = null,
    val genres: List<Int>? = null,
    val likedBy: List<Int>? = null,
    val includedIn: List<Int>? = null,
    val ranking: Int? = null
)