package com.example.myapplication1.ui.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication1.data.model.Song
import com.example.myapplication1.data.repository.RemoteRepository
import kotlinx.coroutines.launch

class SongViewModel : ViewModel() {
    private val repository = RemoteRepository()

    private val _song = MutableLiveData<Song?>()
    val song: LiveData<Song?> = _song

    fun loadSongById(id: Int) {
        viewModelScope.launch {
            try {
                _song.value = repository.getSongById(id)
            } catch (e: Exception) {
                Log.e("SongViewModel", "오류: ${e.message}")
            }
        }
    }

    private val _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> = _songs

    fun loadSongsByIdList(ids: List<Int>) {
        viewModelScope.launch {
            try {
                val result = ids.mapNotNull { id ->
                    try {
                        repository.getSongById(id)
                    } catch (e: Exception) {
                        Log.w("SongViewModel", "곡 로딩 실패 (id=$id): ${e.message}")
                        null
                    }
                }
                _songs.value = result
            } catch (e: Exception) {
                Log.e("SongViewModel", "오류: ${e.message}")
            }
        }
    }

}

