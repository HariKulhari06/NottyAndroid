/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    windowSizeClass: WindowSizeClass
) {
    composable(route = NotesDestination.route) {
        NotesRoute(windowSizeClass)
    }
}
