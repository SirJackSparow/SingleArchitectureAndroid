package com.example.singlearchitecture.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.example.singlearchitecture.ui.detail.DetailScreen
import com.example.singlearchitecture.ui.detail.DetailViewModel
import com.example.singlearchitecture.ui.home.HomeContent
import com.example.singlearchitecture.ui.home.HomeScreen
import com.example.singlearchitecture.ui.home.HomeViewModel

@Composable
fun SingleActivityApp(
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    appState: SingleArchitectureAppState = rememberSingleActivityState()
) {
    if (appState.isOnline) {
        NavHost(navController = appState.navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) { navBackStackEntry ->
                val vm: HomeViewModel = hiltViewModel()
                HomeScreen(
                    vm = vm,
                    navigate = { route -> appState.navigateToDetail(route, navBackStackEntry) })
            }
            composable(Screen.Detail.route) { navBackStackEntry ->
                val vm: DetailViewModel = hiltViewModel()
                DetailScreen(vm = vm)
            }
        }
    }
}