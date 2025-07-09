package com.example.myapplication1.data.api
import com.example.myapplication1.data.model.FriendInfo
import com.example.myapplication1.data.model.Profile
import com.example.myapplication1.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Query
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.DELETE
import java.security.SecureRandom

interface FriendsApi {
    @GET("friends/{id}")
    suspend fun getFriends(@Path("id") id: Int, @Query("type") type: String = "all"): List<FriendInfo>

    @GET("friends/{id}/search")
    suspend fun isAddable(@Path("id") id: Int, @Query("targetName") targetName: String): SearchResponse

    @POST("friends/{id}/add")
    suspend fun addFriend(@Path("id") id: Int, @Query("targetId") targetId: Int): Response<Unit>

    @DELETE("friends/{id}/remove/{targetId}")
    suspend fun removeFriend(@Path("id") id: Int, @Path("targetId") targetId: Int): SearchResponse

    @POST("friends/{id}/close/add/{targetId}")
    suspend fun addCloseFriend(@Path("id") id: Int, @Path("targetId") targetId: Int): SearchResponse

    @DELETE("friends/{id}/close/remove/{targetId}")
    suspend fun removeCloseFriend(@Path("id") id: Int, @Path("targetId") targetId: Int): SearchResponse
}