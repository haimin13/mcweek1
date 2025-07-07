package com.example.myapplication1.ui.components.dialog

import android.app.appsearch.SearchResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun AddFriendDialog(
    showDialog: Boolean,
    myNickname: String,
    currentFriends: List<String>,
    onDismiss: () -> Unit,
    onAddFriend: (String) -> Unit,
    onSearchUser: (String) -> Boolean
) {
    data class UserSearchResult(
        val nickname: String,
        val exists: Boolean
    )

    var searchNickname by remember { mutableStateOf("") }
    var searchResult by remember { mutableStateOf<UserSearchResult?>(null) }// 이름 변경
    var errorMessage by remember { mutableStateOf("") }

    // 검색 함수
    fun searchUser() {
        if (searchNickname.isBlank()) {
            errorMessage = "닉네임을 입력해주세요"
            searchResult = null
            return
        }

        val userExists = onSearchUser(searchNickname)
        searchResult = UserSearchResult(searchNickname, userExists) // 변경된 이름 사용

        errorMessage = when {
            searchNickname == myNickname -> "자신을 친구로 추가할 수 없습니다"
            !userExists -> "존재하지 않는 사용자입니다"
            currentFriends.contains(searchNickname) -> "이미 친구로 등록된 사용자입니다"
            else -> ""
        }
    }

    // Send 버튼 활성화 조건
    val canSendRequest = searchResult?.exists == true &&
            searchNickname != myNickname &&
            !currentFriends.contains(searchNickname) &&
            errorMessage.isEmpty()

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text("친구 추가")
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // 검색 입력 필드
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = searchNickname,
                            onValueChange = {
                                searchNickname = it
                                searchResult = null
                                errorMessage = ""
                            },
                            label = { Text("닉네임 검색") },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            isError = errorMessage.isNotEmpty()
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(
                            onClick = { searchUser() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "검색"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 에러 메시지 표시
                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    // 검색 결과 표시
                    searchResult?.let { result ->
                        if (result.exists && errorMessage.isEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "사용자를 찾았습니다: ${result.nickname}",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onAddFriend(searchNickname)
                        onDismiss()
                    },
                    enabled = canSendRequest
                ) {
                    Text("Send")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text("취소")
                }
            }
        )
    }
}
