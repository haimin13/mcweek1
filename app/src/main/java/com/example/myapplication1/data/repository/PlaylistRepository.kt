package com.example.myapplication1.data.repository

import com.example.myapplication1.data.model.Playlist
import com.example.myapplication1.network.RetrofitInstance

class PlaylistRepository {
  private val api = RetrofitInstance.playlistApi

  suspend fun addPlaylist(playlist: Playlist) = api.addPlaylist(playlist)
  suspend fun getMyPlaylistsByUser(id: Int, type: String) = api.getMyPlaylistsByUser(id, type)
  suspend fun getLikedPlaylistsByUser(id: Int, type: String) = api.getLikedPlaylistsByUser(id, type)
  suspend fun getRecentPlaylists() = api.getRecentPlaylists()
  suspend fun getPlaylistById(id: Int) = api.getPlaylistById(id)
}
