package com.hari.notty.feature.notes.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hari.notty.core.navigation.NottyNavigationDestination
import com.hari.notty.feature.notes.NotesRoute

object NotesDestination : NottyNavigationDestination {
    override val route = "notes_route"
    override val destination = "notes_destination"
}

fun NavGraphBuilder.notesGraph(
    windowSizeClass: WindowSizeClass,
    navigateToAddNote: () -> Unit
) {
    composable(route = NotesDestination.route) {
        NotesRoute(
            windowSizeClass = windowSizeClass,
            navigateToAddNote = navigateToAddNote
        )
    }
}
