package com.example.myapplication1.data.api

import com.example.myapplication1.data.model.Playlist
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Query
import retrofit2.http.Path
import retrofit2.Response

interface PlaylistApi {
  @POST("playlists/add")
  suspend fun addPlaylist(@Body playlist: Playlist): Response<String>

  @GET("playlists/list")
  suspend fun getPlaylistsByUser(
      @Query("id") id: Int,
      @Query("type") type: String = "all"
  ): List<Playlist>

  @GET("playlists/recent")
  suspend fun getRecentPlaylists(): List<Playlist>

  @GET("playlists/{id}")
  suspend fun getPlaylistById(@Path("id") id: Int): Playlist
}
