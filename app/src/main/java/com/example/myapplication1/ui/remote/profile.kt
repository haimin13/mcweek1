package com.example.myapplication1.ui.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication1.data.model.Profile
import com.example.myapplication1.data.model.User
import com.example.myapplication1.data.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val repository = ProfileRepository()

    private var _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    fun loadUsers() {
        viewModelScope.launch {
            try {
                val result = repository.listUser()
                Log.d("Users", "유저 갯수: ${result.size}")
                Log.d("Users", "내용: $result")
                _users.value = result
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "오류: ${e.message}")
            }
        }
    }
}