@file:OptIn(ExperimentalLayoutApi::class)

package com.hari.notty.feature.notes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.hari.notty.core.model.data.Note
import com.hari.notty.core.ui.FilterRow
import com.hari.notty.core.ui.component.NottyGradientBackground
import com.hari.notty.core.ui.theme.NottyLightBlue
import com.hari.notty.core.ui.theme.NottyLightGreen
import com.hari.notty.core.ui.theme.NottyLightPink
import com.hari.notty.core.ui.theme.NottyLightYellow

@Composable
fun NotesRoute(
    windowSizeClass: WindowSizeClass,
    navigateToAddNote: () -> Unit,
    notesViewModel: NotesViewModel = hiltViewModel()
) {
    NotesScreen(
        notes = notesViewModel.notes.collectAsLazyPagingItems(),
        navigateToAddNote = navigateToAddNote
    )
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class
)
@Composable
fun NotesScreen(
    notes: LazyPagingItems<Note>,
    navigateToAddNote: () -> Unit
) {
    NottyGradientBackground {
        Scaffold(
            topBar = {
                NotesTopAppBar()
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = navigateToAddNote,
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                ) {
                    Icon(painter = painterResource(id = R.drawable.add), contentDescription = null)
                }
            }
        ) { innerPadding ->
            NotesGrid(
                modifier = Modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding),
                notes = notes
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
                text = "Notty",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesGrid(
    modifier: Modifier = Modifier,
    notes: LazyPagingItems<Note>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
    ) {
        stickyHeader(key = "w1") {
            FilterRow(lable = "List Notes")
        }
        items(
            items = notes,
            key = { it.id }
        ) {
            NoteCardItem()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCardItem(modifier: Modifier = Modifier) {
    val colors = mutableListOf<Color>(
        NottyLightBlue,
        NottyLightGreen,
        NottyLightPink,
        NottyLightYellow
    )
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.outlinedCardColors(containerColor = colors.random()),
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
fun NotesBottomBar(
    onClickFloatingActionButton: () -> Unit
) {
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
                FloatingActionButton(onClick = onClickFloatingActionButton) {
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
    NotesBottomBar(onClickFloatingActionButton = {})
}

@Preview
@Composable
fun NoteCartItemPreview() {
    NoteCardItem()
}

@Preview
@Composable
fun NotesGridPreview() {
    /*NotesGrid(
        notes = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    )*/
}

@Preview
@Composable
fun NotesScreenPreview() {
    // NotesScreen(navigateToAddNote = {}, notes = notesViewModel.notes.collectAsLazyPagingItems())
}
