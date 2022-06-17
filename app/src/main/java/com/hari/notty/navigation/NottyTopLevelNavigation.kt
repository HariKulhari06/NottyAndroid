package com.hari.notty.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.outlined.Upcoming
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.hari.notty.R
import com.hari.notty.feature.addnote.navigation.AddNoteDestination
import com.hari.notty.feature.category.R.string.categories
import com.hari.notty.feature.category.navigation.CategoryDestination
import com.hari.notty.feature.notes.R.string.notes
import com.hari.notty.feature.notes.navigation.NotesDestination
import com.hari.notty.feature.welcome.navigation.WelcomeDestination

/**
 * Routes for the different top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */

/**
 * Models the navigation top level actions in the app.
 */
class NottyTopLevelNavigation(private val navController: NavHostController) {

    fun navigateTo(destination: TopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

data class TopLevelDestination(
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val iconTextId: Int
)


val TOP_LEVEL_DESTINATIONS = listOf(
    TopLevelDestination(
        route = NotesDestination.route,
        selectedIcon = R.drawable.home,
        unselectedIcon = R.drawable.home,
        iconTextId = notes
    ), TopLevelDestination(
        route = NotesDestination.route,
        selectedIcon = R.drawable.search_status,
        unselectedIcon = R.drawable.search_status,
        iconTextId = notes
    ), TopLevelDestination(
        route = CategoryDestination.route,
        selectedIcon = R.drawable.menu,
        unselectedIcon = R.drawable.menu,
        iconTextId = categories
    ), TopLevelDestination(
        route = CategoryDestination.route,
        selectedIcon = R.drawable.settings,
        unselectedIcon = R.drawable.settings,
        iconTextId = notes
    )
)

