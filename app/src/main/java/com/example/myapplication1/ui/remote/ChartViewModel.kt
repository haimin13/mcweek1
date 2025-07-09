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
            try {
                val result = repository.getFriendsFavorite(userId)
                Log.d("Charts", "FriendsFavorite 차트 노래 수: ${result.songs.size}")
                _friendsFavorite.value = result
            } catch (e: Exception) {
                Log.e("ChartViewModel", "loadFriendsFavorite 오류: ${e.message}")
            }
        }
    }

    fun loadTrendingNow(userId: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getTrendingNow(userId)
                Log.d("Charts", "내가 만든 플레이리스트 갯수: ${result.songs.size}")
                _trendingNow.value = result
            } catch (e: Exception) {
                Log.e("ChartViewModel", "loadTrendingNow 차트 노래 수: ${e.message}")
            }
        }
    }
}