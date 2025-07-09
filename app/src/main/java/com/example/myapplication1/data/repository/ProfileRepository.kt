package com.example.myapplication1.data.repository

import com.example.myapplication1.data.model.Profile
import com.example.myapplication1.network.RetrofitInstance

class ProfileRepository {
  private val api = RetrofitInstance.profileApi

  suspend fun getProfile(id: Int) = api.getProfile(id)
  suspend fun listUser() = api.listUser()
}
