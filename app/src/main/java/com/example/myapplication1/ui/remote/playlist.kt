package com.example.myapplication1.ui.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication1.data.model.Playlist
import com.example.myapplication1.data.repository.PlaylistRepository
import kotlinx.coroutines.launch

class PlaylistViewModel : ViewModel() {
    private val repository = PlaylistRepository()

    private val _playlists = MutableLiveData<List<Playlist>>()
    val playlists: LiveData<List<Playlist>> = _playlists

    private val _myPlaylists = MutableLiveData<List<Playlist>>()
    val myPlaylists: LiveData<List<Playlist>> = _myPlaylists

    private val _likedPlaylists = MutableLiveData<List<Playlist>>()
    val likedPlaylists: LiveData<List<Playlist>> = _likedPlaylists

    fun loadMyPlaylists(userId: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getMyPlaylistsByUser(userId, "created")
                Log.d("Playlists", "내가 만든 플레이리스트 갯수: ${result.size}")
                _myPlaylists.value = result
            } catch (e: Exception) {
                Log.e("PlaylistViewModel", "loadMyPlaylists 오류: ${e.message}")
            }
        }
    }

    fun loadLikedPlaylists(userId: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getLikedPlaylistsByUser(userId, "liked")
                Log.d("Playlists", "좋아요한 플레이리스트 갯수: ${result.size}")
                _likedPlaylists.value = result
            } catch (e: Exception) {
                Log.e("PlaylistViewModel", "loadLikedPlaylists 오류: ${e.message}")
            }
        }
    }

    fun loadRecentPlaylists() {
        viewModelScope.launch {
            try {
                val result = repository.getRecentPlaylists()
                Log.d("Playlists", "최근 생성된 플레이리스트 갯수: ${result.size}")
                _playlists.value = result
            } catch (e: Exception) {
                Log.e("PlaylistViewModel", "loadRecentPlaylists 오류: ${e.message}")
            }
        }
    }
}

