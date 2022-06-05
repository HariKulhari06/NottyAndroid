package com.hari.notty.feature.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hari.notty.core.ui.component.NottyGradientBackground

@Composable
fun NotesRoute(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    notesViewModel: NotesViewModel = hiltViewModel()
) {
    val count by notesViewModel.count.collectAsState(0)
    NotesScreen(
        windowSizeClass,
        modifier,
        count,
        incrementCount = { notesViewModel.increment() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier,
    count: Int,
    incrementCount: () -> Unit
) {
    NottyGradientBackground {
        Scaffold { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                Text(text = "Count value is ${count}")
                Button(onClick = incrementCount) {
                    Text(text = "Increment")
                }
            }
        }
    }
}
