package com.example.myapplication1.ui.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication1.data.model.Artist
import com.example.myapplication1.data.model.ChartResponse
import com.example.myapplication1.data.model.Genre
import com.example.myapplication1.data.model.Song
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

    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>> = _artists

    private val loadedIds = mutableSetOf<Int>() // 캐시 추가

    fun loadArtistsByIdList(ids: List<Int>) {
        val toLoad = ids.filterNot { loadedIds.contains(it) }
        if (toLoad.isEmpty()) return

        viewModelScope.launch {
            try {
                val newArtists = toLoad.mapNotNull { id ->
                    try {
                        repository.getArtistById(id)
                    } catch (e: Exception) {
                        Log.w("ArtistViewModel", "아티스트 로딩 실패 (id=$id): ${e.message}")
                        null
                    }
                }

                val currentArtists = _artists.value.orEmpty()
                val merged = (currentArtists + newArtists)
                    .distinctBy { it.id }

                _artists.value = merged
                loadedIds.addAll(toLoad) // 캐시에 추가
            } catch (e: Exception) {
                Log.e("ArtistViewModel", "오류: ${e.message}")
            }
        }
    }
}

