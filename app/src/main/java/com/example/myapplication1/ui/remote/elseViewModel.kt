package com.example.myapplication1.ui.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication1.data.model.Artist
import com.example.myapplication1.data.model.ChartResponse
import com.example.myapplication1.data.model.Genre
import com.example.myapplication1.data.model.UserLikeLog
import com.example.myapplication1.data.repository.RemoteRepository
import kotlinx.coroutines.launch

class FriendLogViewModel : ViewModel() {
    private val repository = RemoteRepository()
    private val _friendLogs = MutableLiveData<List<UserLikeLog>>()
    val friendLogs: LiveData<List<UserLikeLog>> = _friendLogs

    fun loadFriendLogs(userId: Int) {
        viewModelScope.launch {
            try {
                val res = repository.getFriendsLog(userId)
//                if (res.isSuccessful) _friendLogs.value = res.body()
                _friendLogs.value = res
            } catch (e: Exception) {
                Log.e("FriendLogViewModel", "Error: ${e.message}")
            }
        }
    }
}

class GenreViewModel : ViewModel() {
    private val repository = RemoteRepository()
    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> = _genres

    fun loadGenres() {
        viewModelScope.launch {
            try {
                val res = repository.getGenres()
                 _genres.value = res
//                if (res.isSuccessful) _genres.value = res.body()
            } catch (e: Exception) {
                Log.e("GenreViewModel", "Error: ${e.message}")
            }
        }
    }
}

class ArtistViewModel : ViewModel() {
    private val repository = RemoteRepository()
    private val _artist = MutableLiveData<Artist?>()
    val artist: LiveData<Artist?> = _artist

    fun loadArtistById(id: Int) {
        viewModelScope.launch {
            try {
                val res = repository.getArtistById(id)
                _artist.value = res
//                if (res.isSuccessful) _artist.value = res.body()
            } catch (e: Exception) {
                Log.e("ArtistViewModel", "Error: ${e.message}")
            }
        }
    }
}

