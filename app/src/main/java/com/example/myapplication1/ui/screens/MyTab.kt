package com.example.myapplication1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication1.R

@Composable
fun MyTabMain(modifier: Modifier = Modifier) {
    var myId by remember { mutableIntStateOf(0) }

    var nicknames = listOf(
        "lil monkey",
        "jaedungg",
        "haimin13"
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "My Profile"
        )
        Box(){
            Image(
                painter = painterResource(R.drawable.dummy),
                contentDescription = null
            )
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(3.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                )
            }
        }
        Row(
            modifier = Modifier
        ) {
            Text(
                text = nicknames[myId]
            )
            IconButton(
                onClick = {  }
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                )
            }
        }
    }
}