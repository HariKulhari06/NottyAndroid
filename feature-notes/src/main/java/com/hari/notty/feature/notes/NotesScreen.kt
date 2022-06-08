package com.hari.notty.feature.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun NotesScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier,
    count: Int,
    incrementCount: () -> Unit
) {
    NottyGradientBackground {
        Scaffold(
            topBar = {
                NotesTopAppBar()
            },
            bottomBar = { NotesBottomBar() }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                item { NotesCategorySelector() }
            }
        }
    }
}

@Composable
fun NotesBottomBar() {
    Surface(
        shape = MaterialTheme.shapes.large.copy(
            bottomEnd = CornerSize(0.dp),
            bottomStart = CornerSize(0.dp)
        ),
    ) {
        BottomAppBar(
            tonalElevation = 8.dp,
            icons = {
                IconButton(onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.home), contentDescription = null )
                }
                IconButton(onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.crown), contentDescription = null )
                }
                IconButton(onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.menu), contentDescription = null )
                }
                IconButton(onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.settings), contentDescription = null )
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {  }) {
                    Icon(painter = painterResource(id = R.drawable.add), contentDescription = null )
                }
            }
        )
    }

}


@Preview
@Composable
fun NotesBottomBarPreview() {
    NotesBottomBar()
}

@Composable
private fun NotesTopAppBar() {
    SmallTopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = {
            Text(
                text = "Notty"
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(horizontal = 16.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.search_status),
                    contentDescription = null
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null
                )
            }
        }
    )
}


@Composable
fun NotesCategorySelector() {
    var tabIndex by remember { mutableStateOf(0) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        shape = MaterialTheme.shapes.small,
    ) {
        TabRow(
            modifier = Modifier.height(50.dp),
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary,
            indicator = {}
        ) {
            (0..1).forEach { index ->
                Tab(
                    modifier = Modifier.background(if (index == tabIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer),
                    selected = index == tabIndex,
                    onClick = { tabIndex = index },
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = .4f)
                ) {
                    Text(
                        text = if (index == 0) "Notes" else "Favorite Notes",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun NotesCategorySelectorPreview() {
    NotesCategorySelector()
}

@Preview
@Composable
fun NotesTopAppBarPreview() {
    NotesTopAppBar()
}
