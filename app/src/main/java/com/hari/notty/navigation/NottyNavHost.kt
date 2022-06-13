package com.hari.notty.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.hari.notty.feature.addnote.navigation.AddNoteDestination
import com.hari.notty.feature.addnote.navigation.addNoteGraph
import com.hari.notty.feature.category.navigation.categoryGraph
import com.hari.notty.feature.notes.navigation.NotesDestination
import com.hari.notty.feature.notes.navigation.notesGraph
import com.hari.notty.feature.welcome.navigation.WelcomeDestination
import com.hari.notty.feature.welcome.navigation.welcomeGraph

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun NottyNavHost(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = WelcomeDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        welcomeGraph(
            windowSizeClass = windowSizeClass,
            navigateToNextScreen = {
                navController.navigate(
                    route = NotesDestination.route,
                    navOptions {
                        launchSingleTop = true
                        popUpTo(WelcomeDestination.route) {
                            inclusive = true
                        }
                    }
                )
            }
        )

        notesGraph(
            windowSizeClass = windowSizeClass,
            navigateToAddNote = { navController.navigate(AddNoteDestination.route) }
        )

        addNoteGraph(
            windowSizeClass = windowSizeClass,
            navController = navController
        )

        categoryGraph(
            windowSizeClass = windowSizeClass,
            navController = navController
        )
    }
}
