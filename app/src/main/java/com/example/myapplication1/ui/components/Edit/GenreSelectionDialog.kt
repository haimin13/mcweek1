package com.example.myapplication1.ui.components.Edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.components.common.RoundTag

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenreSelectionDialog(
    showDialog: Boolean,
    currentSelectedGenres: List<Int>,
    onGenresUpdate: (List<Int>) -> Unit,
    onDismiss: () -> Unit,
    maxSelection: Int = 10
) {
    val availableGenres = listOf(
        "R&B", "Hiphop", "Pop", "Rock", "Country", "Indie", "Idol",
        "Electronica", "Jazz", "Classics", "Blues", "Punk", "Reggae",
        "Latin", "Folk", "Trot", "Acoustic", "Ballad", "Crossover"
    )

    var selectedGenres by remember(showDialog) {
        mutableStateOf(currentSelectedGenres) // MutableList 제거
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = "Choose preferences",
                    fontWeight = FontWeight.Medium
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        availableGenres.forEachIndexed { index, genre ->
                            val isSelected = selectedGenres.contains(index)

                            RoundTag(
                                text = genre,
                                fontSize = 14,
                                isSelected = isSelected,
                                onClick = {
                                    if (isSelected) {
                                        // 선택 해제
                                        selectedGenres = selectedGenres.filter { it != index }
                                    } else {
                                        // 선택 추가 (최대 개수 확인)
                                        if (selectedGenres.size < maxSelection) {
                                            selectedGenres = selectedGenres + index
                                        }
                                    }
                                }
                            )
                        }
                    }

                    // 선택된 장르 표시를 맨 아래로 이동
                    Text(
                        text = "${selectedGenres.size} / $maxSelection selected ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (selectedGenres.size > maxSelection)
                            MaterialTheme.colorScheme.error
                        else if (selectedGenres.size == maxSelection)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onGenresUpdate(selectedGenres.toList())
                        onDismiss()
                    },
                    enabled = selectedGenres.size <= maxSelection // 10개 이하이고 비어있지 않을 때만 활성화
                ) {
                    Text(
                        text = "Apply",
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss
                ) {
                    Text(
                        text = "Cancel",
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        )
    }
}
