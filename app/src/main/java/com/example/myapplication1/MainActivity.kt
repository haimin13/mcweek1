package com.example.myapplication1

import com.example.myapplication1.ui.screens.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TextButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.myapplication1.ui.theme.MyApplication1Theme
import com.example.myapplication1.ui.screens.PlaylistsTabMain
import com.example.myapplication1.ui.screens.playlists.PlaylistDetailScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.navArgument
import com.example.myapplication1.ui.components.models.Playlist
import com.example.myapplication1.ui.screens.playlists.findPlaylistById


sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home: BottomNavItem("home", "home", Icons.Outlined.Home)
    object Playlists: BottomNavItem("playlists", "playlists", Icons.Outlined.PlayArrow)
    object Friends: BottomNavItem("friends", "friends", Icons.Outlined.Favorite)
    object My: BottomNavItem("my", "my", Icons.Outlined.AccountCircle)
}


@Composable
fun MainScreen(modifier: Modifier) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Playlists,
        BottomNavItem.Friends,
        BottomNavItem.My
    )
    // 탭별 시작 destination 매핑
    val tabStartDestinations = mapOf(
        "home" to "homeMain",
        "playlists" to "playlistsMain",
        "friends" to "friendsMain",
        "my" to "myMain"
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    val startDestination = tabStartDestinations[item.route] ?: item.route
                    val isInCurrentTab = isRouteInTab(currentRoute, item.route)

                    NavigationBarItem(
                        selected = isInCurrentTab,
                        onClick = {
                            if (isInCurrentTab) {
                                // 같은 탭: 시작점으로 이동
                                navController.popBackStack(startDestination, false)
                            } else {
                                val currentTabRoute = getCurrentTabRoute(currentRoute)
                                currentTabRoute?.let { tabRoute ->
                                    val currentTabStartDestination = tabStartDestinations[tabRoute]
                                    if (currentTabStartDestination != null && currentRoute != currentTabStartDestination) {
                                        navController.popBackStack(currentTabStartDestination, false)
                                    }
                                }
                                // 다른 탭: 해당 탭으로 이동
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.My.route,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            navigation(startDestination = "homeMain", route = BottomNavItem.Home.route) {
                composable("homeMain") { HomeTabMain() }
            }
            navigation(startDestination = "playlistsMain", route = BottomNavItem.Playlists.route) {
                composable("playlistsMain") { PlaylistsTabMain(modifier = Modifier, navController = navController)}
            }
            navigation(startDestination = "friendsMain", route = BottomNavItem.Friends.route) {
                composable("friendsMain") {
                    FriendsTabMain(
                        onNotificationClick = { navController.navigate("friends/notifications") }
                    )
                }
                composable("friends/notifications") {
                    NotificationPage (
                        onBackClick = { navController.popBackStack() }
                    )
                }
            }
            navigation(startDestination = "myMain", route = BottomNavItem.My.route) {
                composable("myMain") { MyTabMain() }
            }

            // ✅ 추가: 상세 화면 경로
//            this.composable(
            composable(
                route = "playlistDetail/{playlistId}",
                arguments = listOf(navArgument("playlistId") { type = androidx.navigation.NavType.IntType })
            ) { backStackEntry ->
                val playlistId = backStackEntry.arguments?.getInt("playlistId") ?: return@composable
                val playlist = findPlaylistById(playlistId)
                PlaylistDetailScreen(playlist = playlist, navController = navController)
            }
        }
    }
}

private fun isRouteInTab(currentRoute: String?, tabRoute: String): Boolean {
    return when (tabRoute) {
        "home" -> currentRoute == "homeMain"
        "playlists" -> currentRoute == "playlistsMain"
        "friends" -> currentRoute in listOf("friendsMain", "friends/notifications")
        "my" -> currentRoute == "myMain"
        else -> false
    }
}

private fun getCurrentTabRoute(currentRoute: String?): String? {
    return when (currentRoute) {
        "homeMain" -> "home"
        "playlistsMain" -> "playlists"
        "friendsMain", "friends/notifications" -> "friends"
        "myMain" -> "my"
        else -> null
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    MainScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                    )
                }
            }
        }
    }
}

/*enum class Destinations(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    PLAYLISTS("playlists", "Playlists", Icons.Default.PlayArrow, "Playlists"),
    GALLERY("gallery", "Gallery", Icons.Default.Favorite, "Gallery"),
    ANYTHING("anything", "Anything", Icons.Default.MoreVert, "Anything")
}

@Composable
fun GalleryScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Playlists Screen")
    }
}

@Composable
fun AnythingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Anything Screen")
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destinations,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destinations.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destinations.PLAYLISTS -> PlaylistsTabMain()
                    Destinations.GALLERY -> GalleryScreen()
                    Destinations.ANYTHING -> AnythingScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tabs(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destinations.PLAYLISTS
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    Column(modifier = modifier) {
        PrimaryTabRow(
            selectedTabIndex = selectedDestination,
        ) {
            Destinations.entries.forEachIndexed { index, destination ->
                Tab(
                    selected = selectedDestination == index,
                    onClick = {
                        navController.navigate(route = destination.route)
                        selectedDestination = index
                    },
                    text = {
                        Text(
                            text = destination.label,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }
        AppNavHost(navController, startDestination)
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication1Theme {
        Tabs()
    }
}*/