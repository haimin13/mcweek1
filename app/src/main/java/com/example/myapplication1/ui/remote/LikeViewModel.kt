package com.example.myapplication1.ui.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication1.data.model.ChartResponse
import com.example.myapplication1.data.repository.RemoteRepository
import kotlinx.coroutines.launch

class LikeViewModel : ViewModel() {
    private val repository = RemoteRepository()

    fun like(userId: Int, itemType: String, itemId: Int) {
        viewModelScope.launch {
            try {
                repository.likeItem(userId, itemType, itemId)
            } catch (e: Exception) {
                Log.e("LikeViewModel", "Like Error: ${e.message}")
            }
        }
    }

    fun unlike(userId: Int, itemType: String, itemId: Int) {
        viewModelScope.launch {
            try {
                repository.unlikeItem(userId, itemType, itemId)
            } catch (e: Exception) {
                Log.e("LikeViewModel", "Unlike Error: ${e.message}")
            }
        }
    }
}
