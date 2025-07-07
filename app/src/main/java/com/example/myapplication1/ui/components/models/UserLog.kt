package com.example.myapplication1.ui.components.models

import androidx.annotation.DrawableRes
import java.time.LocalDateTime

data class UserLog(
    @DrawableRes val thumbnailResId: Int? = null,
    val userId: Int = 0,
    val userName: String,
    val itemName: String,
    val time: LocalDateTime = LocalDateTime.now(),
)