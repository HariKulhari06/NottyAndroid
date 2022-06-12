@file:OptIn(ExperimentalLayoutApi::class)

package com.hari.notty.feature.notes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hari.notty.core.ui.component.NottyGradientBackground

@Composable
fun NotesRoute(
    windowSizeClass: WindowSizeClass,
    notesViewModel: NotesViewModel = hiltViewModel()
) {
    val count by notesViewModel.count.collectAsState(0)
    NotesScreen()
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun NotesScreen(
) {
    NottyGradientBackground {
        Scaffold(
            topBar = {
                NotesTopAppBar()
            },
            bottomBar = { NotesBottomBar() }
        ) { innerPadding ->
            NotesGrid(
                modifier = Modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding),
                notes = listOf(1,2,3,4,5,6,7,8,9)
            )
        }
    }
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
                contentDescription = null
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
fun NotesCategorySelector(
    modifier: Modifier = Modifier
) {
    var tabIndex by remember { mutableStateOf(0) }
    Surface(
        modifier = modifier,
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
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}


@Composable
fun NotesGrid(
    modifier: Modifier = Modifier,
    notes: List<Int>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
    ) {
        items(notes, key = { it }) {
            NoteCardItem()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCardItem(modifier: Modifier = Modifier) {
    OutlinedCard(
        modifier = modifier,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.outlinedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        onClick = { }
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "How to Draw a Professional Wireframe?",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = "Wireframing for everyone. Create stunning prototypes without prior experience. The easiest way to test an idea, create wireframes online. No tutorial needed. Rapid prototyping. Wand-erful templates. Theme extractor. Start in 2 minutes. Apps & website templates.",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                softWrap = true

            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Design | Wireframe",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Light)
                )
                Text(
                    text = "2020/05/19",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Light)
                )

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
            modifier = Modifier.navigationBarsPadding(),
            tonalElevation = 8.dp,
            icons = {
                IconButton(onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.home), contentDescription = null)
                }
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.crown),
                        contentDescription = null
                    )
                }
                IconButton(onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.menu), contentDescription = null)
                }
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.add), contentDescription = null)
                }
            }
        )
    }

}


@Preview
@Composable
fun NotesTopAppBarPreview() {
    NotesTopAppBar()
}

@Preview
@Composable
fun NotesCategorySelectorPreview() {
    NotesCategorySelector()
}


@Preview
@Composable
fun NotesBottomBarPreview() {
    NotesBottomBar()
}

@Preview
@Composable
fun NoteCartItemPreview() {
    NoteCardItem()
}

@Preview
@Composable
fun NotesGridPreview() {
    NotesGrid(
        notes = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    )
}

@Preview
@Composable
fun NotesScreenPreview() {
    NotesScreen()
}
