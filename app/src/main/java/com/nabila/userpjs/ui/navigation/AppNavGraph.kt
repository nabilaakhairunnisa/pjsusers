package com.nabila.userpjs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nabila.userpjs.ui.detail.DetailScreen
import com.nabila.userpjs.ui.detail.DetailViewModel
import com.nabila.userpjs.ui.main.MainScreen
import com.nabila.userpjs.ui.main.MainViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun AppNavGraph(mainViewModel: MainViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "user_list",
        modifier = modifier
    ) {

        // List Screen
        composable("user_list") {
            MainScreen(viewModel = mainViewModel, onCardClick = { user ->
                navController.navigate("user_detail/${user.id}")
            })
        }

        // Detail Screen
        composable(
            route = "user_detail/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0

            val detailViewModel: DetailViewModel = koinViewModel()

            DetailScreen(
                userId = userId,
                viewModel = detailViewModel,
                onBack = { navController.popBackStack() }
            )
        }

    }
}
