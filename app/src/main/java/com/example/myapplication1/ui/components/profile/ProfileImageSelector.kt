package com.example.myapplication1.ui.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.R

data class ProfileImage(
    val id: Int,
    val resourceId: Int,
    val name: String
)

@Composable
fun ProfileImageSelector(
    showDialog: Boolean,
    currentImageId: Int,
    onImageSelected: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    // 사용 가능한 프로필 이미지들
    val profileImages = listOf(
        ProfileImage(0, R.drawable.profile_default, "Default"),
        ProfileImage(1, R.drawable.profile_cat, "Cat"),
        ProfileImage(2, R.drawable.profile_dog, "Dog"),
        ProfileImage(3, R.drawable.profile_avatar1, "Avatar 1"),
        ProfileImage(4, R.drawable.profile_avatar2, "Avatar 2"),
        ProfileImage(5, R.drawable.profile_avatar3, "Avatar 3"),
        ProfileImage(6, R.drawable.profile_nature_tree, "Tree"),
        ProfileImage(7, R.drawable.profile_nature_flower, "Flower")
    )

    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "프로필 사진 선택",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        items(profileImages) { profileImage ->
                            ProfileImageItem(
                                profileImage = profileImage,
                                isSelected = profileImage.id == currentImageId,
                                onImageClick = {
                                    onImageSelected(profileImage.id)
                                    onDismiss()
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = onDismiss) {
                            Text("Cancel")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileImageItem(
    profileImage: ProfileImage,
    isSelected: Boolean,
    onImageClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clickable { onImageClick() }
    ) {
        Image(
            painter = painterResource(profileImage.resourceId),
            contentDescription = profileImage.name,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        // 선택된 이미지 표시
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .clickable { onImageClick() },
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    shape = CircleShape
                ) {}

                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
