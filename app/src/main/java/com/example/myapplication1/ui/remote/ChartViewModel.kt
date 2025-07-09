package com.example.myapplication1.ui.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication1.data.model.ChartResponse
import com.example.myapplication1.data.repository.RemoteRepository
import kotlinx.coroutines.launch

class ChartViewModel : ViewModel() {
    private val repository = RemoteRepository()

    private val _friendsFavorite = MutableLiveData<ChartResponse>()
    val friendsFavorite: LiveData<ChartResponse> = _friendsFavorite

    private val _trendingNow = MutableLiveData<ChartResponse>()
    val trendingNow: LiveData<ChartResponse> = _trendingNow

    fun loadFriendsFavorite(userId: Int) {
        viewModelScope.launch {
            _friendsFavorite.value = repository.getFriendsFavorite(userId)
        }
    }

    fun loadTrendingNow(userId: Int) {
        viewModelScope.launch {
            _trendingNow.value = repository.getTrendingNow(userId)
        }
    }
}