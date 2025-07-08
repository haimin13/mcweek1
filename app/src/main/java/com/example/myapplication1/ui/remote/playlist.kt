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

    fun loadPlaylists(userId: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getPlaylistsByUser(userId, "all")
                Log.d("Playlists", "플레이리스트 갯수: ${result.size}")
                Log.d("Playlists", "내용: $result")
                _playlists.value = result
            } catch (e: Exception) {
                Log.e("PlaylistViewModel", "오류: ${e.message}")
            }
        }
    }
}
