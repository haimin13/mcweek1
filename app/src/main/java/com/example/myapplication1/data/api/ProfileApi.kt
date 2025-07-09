package com.example.myapplication1.data.api

import com.example.myapplication1.data.model.Profile
import com.example.myapplication1.data.model.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Query
import retrofit2.http.Path
import retrofit2.Response

interface ProfileApi {
    @GET("profile/{id}")
    suspend fun getProfile(@Path("id") id: Int): Profile

    @GET("profile/list")
    suspend fun listUser(): List<User>
}
