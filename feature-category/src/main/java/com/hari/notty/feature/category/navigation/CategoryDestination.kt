package com.hari.notty.feature.category.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.hari.notty.core.navigation.NottyNavigationDestination
import com.hari.notty.feature.category.CategoryRout

object CategoryDestination : NottyNavigationDestination {
    override val route = "category_route"
    override val destination = "category_destination"
}

fun NavGraphBuilder.categoryGraph(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController
) {
    composable(route = CategoryDestination.route) {
        CategoryRout(
            windowSizeClass = windowSizeClass,
            navController =navController
        )
    }
}
