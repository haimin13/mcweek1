package org.example.data

object PlaylistRepository {
    private val playlists = listOf(
        Playlist(1, "Chill Beats", "2025-07-01"),
        Playlist(2, "Focus Mode", "2025-07-02")
    )

    fun getRecentPlaylists(): List<Playlist> = playlists
}
