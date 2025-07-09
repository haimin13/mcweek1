package com.example.myapplication1.ui.components.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.data.model.Artist
import com.example.myapplication1.ui.components.popup.ArtistDetailPopup

@Composable
fun ArtistList(
    modifier: Modifier = Modifier,
    artists: List<Artist>,
    verticalSpacing: Int = 8,
    isCharts: Boolean = false,
    onItemClick: ((Artist) -> Unit)? = null,
) {
    var selectedArtist by remember { mutableStateOf<Artist?>(null) }

    GenericList(
        modifier = modifier,
        items = artists,
        verticalSpacing = verticalSpacing.dp,
        onItemClick = { artist ->
            selectedArtist = artist
            onItemClick?.invoke(artist)
        }
    ) { artist ->
        ArtistEntry(artist = artist, isCharts = isCharts)
    }

    selectedArtist?.let { artist ->
        Dialog(onDismissRequest = { selectedArtist = null }) {
            ArtistDetailPopup(artist = artist, onDismiss = { selectedArtist = null })
        }
    }
}

val dummyArtistList = listOf(
    Artist(
        id = 1,
        nickname = "IU",
        thumbnailId = null,
        likedGenres = mutableListOf(1,2),
        likedBy = mutableListOf(1,2)
    ),
    Artist(
        id = 2,
        nickname = "BTS",
        thumbnailId = null,
        likedGenres = mutableListOf(1,2),
        likedBy = mutableListOf(1,2),
    ),
    Artist(
        id = 3,
        nickname = "BLACKPINK",
        thumbnailId = null,
        likedGenres = mutableListOf(1,2),
        likedBy = mutableListOf(1,2),
    ),
    Artist(
        id = 4,
        nickname = "Zico",
        thumbnailId = null,
        likedGenres =  mutableListOf(1,2),
        likedBy =  mutableListOf(1,2),
    ),
    Artist(
        id = 5,
        nickname = "AKMU",
        thumbnailId = null,
        likedGenres =  mutableListOf(1,2),
        likedBy =  mutableListOf(1,2),
    ),
    Artist(
        id = 6,
        nickname = "Taeyeon",
        thumbnailId = null,
        likedGenres =  mutableListOf(1,2),
        likedBy =  mutableListOf(1,2),
    )
)

