package com.example.myapplication1.ui.components.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.R

@Composable
fun GalleryTap(
    userId: String,
    onDismiss: () -> Unit
){
    Surface(
        modifier = Modifier.size(400.dp, 550.dp),
        color = Color.White,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp
    ) {
        Box(
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onDismiss
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(vertical = 35.dp, horizontal = 30.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userId + "'s music profile",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(R.drawable.dummy),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(RoundedCornerShape(10))
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Music Preference",
                        fontWeight = FontWeight.Bold
                    )
                    // CustomLazyRow(장르)
                    Spacer(modifier = Modifier.height(25.dp)) // 임시
                    Text(
                        text = "Liked Song",
                        fontWeight = FontWeight.Bold
                    )
                    // CustomLazyRow(곡)
                    Spacer(modifier = Modifier.height(40.dp)) // 임시
                    Text(
                        text = "Playlists",
                        fontWeight = FontWeight.Bold
                    )
                    // CustomLazyRow(플리)
                    Spacer(modifier = Modifier.height(30.dp)) // 임시
                    Text(
                        text = "Favorite Artists",
                        fontWeight = FontWeight.Bold
                    )
                    // CustomLazyRow(가수)
                    Spacer(modifier = Modifier.height(30.dp)) // 임시
                }
            }
        }
    }
}