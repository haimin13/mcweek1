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
}

