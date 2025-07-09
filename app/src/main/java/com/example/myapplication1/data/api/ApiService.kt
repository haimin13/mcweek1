package com.example.myapplication1.data.api

import com.example.myapplication1.data.model.Artist
import com.example.myapplication1.data.model.ChartResponse
import com.example.myapplication1.data.model.Genre
import com.example.myapplication1.data.model.Playlist
import com.example.myapplication1.data.model.Song
import com.example.myapplication1.data.model.UserLikeLog
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Query
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.DELETE

interface ApiService {
  @GET("/songs/{id}")
  suspend fun getSongById(@Path("id") id: Int): Song

  @GET("/charts/friendsfavorite")
  suspend fun getFriendsFavorite(@Query("id") userId: Int): ChartResponse

  @GET("/charts/trendingnow")
  suspend fun getTrendingNow(@Query("id") userId: Int): ChartResponse

  @GET("/friendsupdate/{id}")
  suspend fun getFriendsLog(@Path("id") id: Int): List<UserLikeLog>

  @GET("/genres")
  suspend fun getGenres(): List<Genre>

  @GET("/artists/{id}")
  suspend fun getArtistById(@Path("id") id: Int): Artist

  @POST("/likes/add")
  suspend fun likeItem(
    @Query("userId") userId: Int,
    @Query("itemType") itemType: String,
    @Query("itemId") itemId: Int
  ): ResponseBody

  @DELETE("/likes/remove")
  suspend fun unlikeItem(
    @Query("userId") userId: Int,
    @Query("itemType") itemType: String,
    @Query("itemId") itemId: Int
  ): ResponseBody
}

