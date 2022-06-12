package com.hari.notty.feature.welcome.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hari.notty.core.navigation.NottyNavigationDestination
import com.hari.notty.feature.welcome.WelcomeRoute

object WelcomeDestination : NottyNavigationDestination {
    override val route = "welcome_route"
    override val destination = "welcome_destination"
}

fun NavGraphBuilder.welcomeGraph(
    windowSizeClass: WindowSizeClass,
    navigateToNextScreen: () -> Unit
) {
    composable(route = WelcomeDestination.route) {
        WelcomeRoute(
            windowSizeClass = windowSizeClass,
            coroutineScope = rememberCoroutineScope(),
            navigateToNextScreen = navigateToNextScreen,
        )
    }
}
