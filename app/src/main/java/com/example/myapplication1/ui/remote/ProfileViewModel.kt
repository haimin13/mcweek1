package com.example.myapplication1.ui.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication1.data.model.Profile
import com.example.myapplication1.data.model.Playlist
import com.example.myapplication1.data.repository.PlaylistRepository
import com.example.myapplication1.data.repository.ProfileRepository
import kotlinx.coroutines.launch

// ProfileViewModel.kt
class ProfileViewModel : ViewModel() {
    private val repository = ProfileRepository()

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> = _profile

    fun loadProfileById(id: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getProfileById(id)
                _profile.value = result
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "프로필 로딩 오류: ${e.message}")
            }
        }
    }


}
