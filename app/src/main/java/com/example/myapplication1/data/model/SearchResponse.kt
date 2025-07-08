package com.example.myapplication1.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val success: Boolean,
    val message: String
)