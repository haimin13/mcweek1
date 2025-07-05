//package com.example.myapplication1.ui.components.playlistsTab
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.systemBarsPadding
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Favorite
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material.icons.filled.PlayArrow
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.PrimaryTabRow
//import androidx.compose.material3.Tab
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//import androidx.navigation.NavHostController
//import com.example.myapplication1.ui.theme.MyApplication1Theme
//import com.example.myapplication1.ui.screens.PlaylistsScreen
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//
//enum class Destinations(
//    val route: String,
//    val label: String,
//    val icon: ImageVector,
//    val contentDescription: String
//) {
//    MYPLAYLISTS("myplaylists", "My playlists", Icons.Default.PlayArrow, "Myplaylists"),
//    LIKEDPLAYLISTS("likedplylists", "Liked playlists", Icons.Default.Favorite, "Likedplylists"),
//    CHARTS("charts", "Charts", Icons.Default.MoreVert, "Charts")
//}
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MyApplication1Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
//                    Tabs(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .systemBarsPadding()
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun MyPlaylists(modifier: Modifier = Modifier) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("My playlists")
//    }
//}
//
//@Composable
//fun LikedPlaylists(modifier: Modifier = Modifier) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("Liked playlists")
//    }
//}
//
//@Composable
//fun AppNavHost(
//    navController: NavHostController,
//    startDestination: Destinations,
//    modifier: Modifier = Modifier
//) {
//    NavHost(
//        navController,
//        startDestination = startDestination.route
//    ) {
//        Destinations.entries.forEach { destination ->
//            composable(destination.route) {
//                when (destination) {
//                    Destinations.MYPLAYLISTS -> MyPlaylists()
//                    Destinations.LIKEDPLAYLISTS -> Likedplylists()
//                    Destinations.CHARTS -> Charts()
//                }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PlaylistTabBar(
//    navController: NavHostController,
//    modifier: Modifier = Modifier,
//    startDestination: Destinations,
//    content: @Composable (Destinations) -> Unit
//) {
//    var selectedTabIndex by remember { mutableIntStateOf(startDestination.ordinal) }
//
//    Column(modifier = modifier) {
//        PrimaryTabRow(
//            selectedTabIndex = selectedTabIndex,
//            containerColor = MaterialTheme.colorScheme.background,
//            contentColor = MaterialTheme.colorScheme.primary
//        ) {
//            Destinations.entries.forEachIndexed { index, destination ->
//                Tab(
//                    selected = selectedTabIndex == index,
//                    onClick = {
//                        navController.navigate(destination.route)
//                        selectedTabIndex = index
//                    },
//                    text = {
//                        Text(
//                            text = destination.label,
//                            maxLines = 1,
//                            overflow = TextOverflow.Ellipsis
//                        )
//                    }
//                )
//            }
//        }
//
//        NavHost(navController, startDestination = startDestination.route) {
//            Destinations.entries.forEach { destination ->
//                composable(destination.route) {
//                    content(destination)
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplication1Theme {
//        Tabs()
//    }
//}
//
//
