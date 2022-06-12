package com.hari.notty.feature.addnote.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.hari.notty.core.navigation.NottyNavigationDestination
import com.hari.notty.feature.addnote.AddNoteRout

object AddNoteDestination : NottyNavigationDestination {
    override val route = "add_note_route"
    override val destination = "add_note_destination"
}

fun NavGraphBuilder.addNoteGraph(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController
) {
    composable(route = AddNoteDestination.route) {
        AddNoteRout(
            windowSizeClass = windowSizeClass,
            navController =navController
        )
    }
}
