package com.example.myapplication1.ui.components.playlistsTab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication1.ui.screens.playlists.PlaylistDestinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistTabBar(
    navController: NavHostController,
    startDestination: PlaylistDestinations,
    modifier: Modifier,
    content: @Composable (PlaylistDestinations, Modifier) -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(startDestination.ordinal) }

    Column(modifier = modifier.fillMaxSize()) {
        PrimaryTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            PlaylistDestinations.entries.forEachIndexed { index, destination ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        navController.navigate(destination.fullRoute)
                        selectedTabIndex = index
                    },
                    text = {
                        Text(
                            text = destination.label,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            NavHost(
                navController = navController,
                startDestination = startDestination.fullRoute,
                modifier = Modifier.fillMaxSize()
            ) {
                PlaylistDestinations.entries.forEach { destination ->
                    composable(destination.fullRoute) {
                        content(destination, Modifier.fillMaxSize())
                    }
                }
            }
        }
    }
}
