package com.example.myapplication1.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChartResponse(
    val songs: List<Song>,
    val playlists: List<Playlist>
)