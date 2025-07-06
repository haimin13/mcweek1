package com.example.myapplication1.ui.components.playlistsTab

import androidx.compose.foundation.layout.Column
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
    modifier: Modifier = Modifier,
    content: @Composable (PlaylistDestinations) -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(startDestination.ordinal) }

    Column(modifier = modifier) {
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

        NavHost(
            navController = navController,
            startDestination = startDestination.fullRoute
        ) {
            PlaylistDestinations.entries.forEach { destination ->
                composable(destination.fullRoute) {
                    content(destination)
                }
            }
        }
    }
}
