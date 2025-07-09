package com.example.myapplication1.data.repository

import com.example.myapplication1.data.model.Playlist
import com.example.myapplication1.data.model.Profile
import com.example.myapplication1.network.RetrofitInstance

class ProfileRepository {
  private val api = RetrofitInstance.profileApi

  suspend fun getProfileById(id: Int): Profile {
    return api.getProfileById(id)
  }
}
