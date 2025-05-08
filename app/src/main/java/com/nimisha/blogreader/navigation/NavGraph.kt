package com.nimisha.blogreader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.nimisha.blogreader.ui.bloglist.BlogListScreen
import com.nimisha.blogreader.ui.blogdetail.BlogDetailScreen

@Composable
fun BlogNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            BlogListScreen(navController)
        }
        composable(
            "detail/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            BlogDetailScreen(blogUrl = url)
        }
    }
}
