package com.example.myapplication1.network

import com.example.myapplication1.data.api.PlaylistApi
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

object RetrofitInstance {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") // 실제 API 서버 주소로 변경
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val playlistApi: PlaylistApi by lazy {
        retrofit.create(PlaylistApi::class.java)
    }

    // 필요한 다른 API들도 여기에 추가 가능
    // val userApi: UserApi by lazy { retrofit.create(UserApi::class.java) }
}
