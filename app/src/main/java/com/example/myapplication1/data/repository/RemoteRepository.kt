package com.example.myapplication1.data.repository

import com.example.myapplication1.network.RetrofitInstance

class RemoteRepository() {
  private val api = RetrofitInstance.remoteApi

  suspend fun getSongById(id: Int) = api.getSongById(id)
  suspend fun getFriendsFavorite(userId: Int) = api.getFriendsFavorite(userId)
  suspend fun getTrendingNow(userId: Int) = api.getTrendingNow(userId)
  suspend fun getFriendsLog(userId: Int) = api.getFriendsLog(userId)
  suspend fun getGenres() = api.getGenres()
  suspend fun getArtistById(id: Int) = api.getArtistById(id)
  suspend fun likeItem(userId: Int, itemType: String, itemId: Int) = api.likeItem(userId, itemType, itemId)
  suspend fun unlikeItem(userId: Int, itemType: String, itemId: Int) = api.unlikeItem(userId, itemType, itemId)
}
