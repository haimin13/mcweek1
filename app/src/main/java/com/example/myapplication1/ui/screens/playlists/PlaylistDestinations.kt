package com.example.myapplication1.ui.screens.playlists

enum class PlaylistDestinations(
    val subRoute: String,
    val label: String
) {
    MYPLAYLISTS("myplaylists", "My playlists"),
    LIKEDPLAYLISTS("likedplaylists", "Liked playlists"),
    CHARTS("charts", "Charts");

    companion object {
        const val baseRoute = "playlists"
    }

    val fullRoute: String
        get() = "$baseRoute/$subRoute"
}
