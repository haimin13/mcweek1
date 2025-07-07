package com.example.myapplication1.ui.components.Edit

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight

@Composable
fun EditNickname(
    showDialog: Boolean,
    initialNickname: String,
    existingNicknames: List<String>,
    onNicknameUpdate: (String) -> Unit,
    onDismiss: () -> Unit,
    maxLength: Int = 20
) {
    var tempNickname by remember(showDialog) { mutableStateOf(initialNickname) }
    var errorMessage by remember { mutableStateOf("") }

    fun validateNickname(nickname: String): String {
        return when {
            nickname.isBlank() -> "닉네임을 입력해주세요"
            nickname.length > maxLength -> "닉네임은 ${maxLength}자 이하로 입력해주세요"
            nickname != initialNickname && existingNicknames.contains(nickname) -> "이미 사용 중인 닉네임입니다"
            else -> ""
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(
                text = "Change nickname",
                fontWeight = FontWeight.Medium
            ) },
            text = {
                Column {
                    OutlinedTextField(
                        value = tempNickname,
                        onValueChange = { newValue ->
                            tempNickname = newValue
                            errorMessage = validateNickname(newValue)
                        },
                        label = { Text("Nickname") },
                        singleLine = true,
                        isError = errorMessage.isNotEmpty(),
                        supportingText = {
                            if (errorMessage.isNotEmpty()) {
                                Text(
                                    text = errorMessage,
                                    color = MaterialTheme.colorScheme.error
                                )
                            } else {
                                Text("${tempNickname.length}/$maxLength")
                            }
                        }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val validation = validateNickname(tempNickname)
                        if (validation.isEmpty()) {
                            onNicknameUpdate(tempNickname)
                            onDismiss()
                        } else {
                            errorMessage = validation
                        }
                    },
                    enabled = errorMessage.isEmpty() && tempNickname.isNotBlank()
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}