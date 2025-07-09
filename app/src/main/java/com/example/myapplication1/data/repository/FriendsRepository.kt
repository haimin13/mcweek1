package com.example.myapplication1.data.repository

import com.example.myapplication1.network.RetrofitInstance

class FriendsRepository {
    private val api = RetrofitInstance.friendsApi

    suspend fun getFriends(id: Int, type: String = "all") = api.getFriends(id, type)
    suspend fun isAddable(id: Int, targetName: String) = api.isAddable(id, targetName)
    suspend fun addFriend(id: Int, targetId: Int) = api.addFriend(id, targetId)
    suspend fun removeFriend(id: Int, targetId: Int) = api.removeFriend(id, targetId)
    suspend fun addCloseFriend(id: Int, targetId: Int) = api.addCloseFriend(id, targetId)
    suspend fun removeCloseFriend(id: Int, targetId: Int) = api.removeCloseFriend(id, targetId)
}