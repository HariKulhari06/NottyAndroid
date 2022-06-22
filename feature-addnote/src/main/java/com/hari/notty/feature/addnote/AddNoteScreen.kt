package com.hari.notty.feature.addnote

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hari.notty.core.ui.component.NottyBackground

@Composable
fun AddNoteRout(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController,
    addNoteViewModel: AddNoteViewModel = hiltViewModel()
) {
    val headline = addNoteViewModel.title.collectAsState(initial = "")
    val note = addNoteViewModel.description.collectAsState(initial = "")

    AddNoteScreen(
        windowSizeClass = windowSizeClass,
        navController = navController,
        headline = headline,
        onHeadlineChange = addNoteViewModel::setHeadline,
        note = note,
        onNoteChange = addNoteViewModel::setNote,
        addNote = addNoteViewModel::addNote
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddNoteScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController,
    headline: State<String>,
    onHeadlineChange: (String) -> Unit,
    note: State<String>,
    onNoteChange: (String) -> Unit,
    addNote: () -> Unit,
) {
    NottyBackground {
        Scaffold(
            topBar = {
                AddNoteToolbar(
                    onClickNavigationIcon = { navController.popBackStack() },
                    onClickAddNote = addNote
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding)
                    .fillMaxSize()
            ) {
                HeadlineTextField(
                    modifier = Modifier,
                    headline = headline,
                    onHeadlineChange = onHeadlineChange
                )

                NoteTextField(
                    modifier = Modifier.weight(1f),
                    note = note,
                    onNoteChange = onNoteChange
                )

                NotesSettingsRow(
                    modifier = Modifier
                        .padding(16.dp)
                        .imeNestedScroll()
                )
            }
        }
    }
}

@Composable
fun NotesSettingsRow(
    modifier: Modifier = Modifier,
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(65.dp),
        color = Color.Black,
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            mutableListOf<Painter>(
                painterResource(id = R.drawable.text_bold),
                painterResource(id = R.drawable.text_italic),
                painterResource(id = R.drawable.text_underline),
                painterResource(id = R.drawable.textalign_justifycenter),
                painterResource(id = R.drawable.link),
            ).forEachIndexed { index, painter ->
                IconButton(onClick = { }) {
                    Icon(
                        painter = painter,
                        contentDescription = null,
                        tint = if (index == 0) MaterialTheme.colorScheme.tertiary else Color.Gray
                    )
                }
            }
        }

    }
}


@Composable
fun AddNoteToolbar(
    modifier: Modifier = Modifier,
    onClickNavigationIcon: () -> Unit,
    onClickAddNote: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier.statusBarsPadding(),
        title = {
            Text(
                text = "Add Note",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        },
        navigationIcon = {
            IconButton(onClick = onClickNavigationIcon) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close_circle),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = onClickAddNote) {
                Icon(
                    painter = painterResource(id = R.drawable.tick_circle),
                    contentDescription = null
                )
            }
        }
    )
}


@Composable
fun HeadlineTextField(
    modifier: Modifier = Modifier,
    headline: State<String>,
    onHeadlineChange: (String) -> Unit,
) {

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = headline.value,
        onValueChange = onHeadlineChange,
        placeholder = {
            Text(
                text = "Headline",
                style = MaterialTheme.typography.titleMedium
            )
        },
        textStyle = MaterialTheme.typography.titleMedium,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
    )
}

@Composable
fun NoteTextField(
    modifier: Modifier = Modifier,
    note: State<String>,
    onNoteChange: (String) -> Unit,
) {

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = note.value,
        onValueChange = onNoteChange,
        placeholder = {
            Text(
                text = "Note...",
                style = MaterialTheme.typography.bodyLarge
            )
        },
        textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Justify),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}


@Preview
@Composable
fun AddNoteToolbarPreview() {
    AddNoteToolbar(
        onClickNavigationIcon = {},
        onClickAddNote = {},
    )
}


@Preview()
@Composable
fun HeadlineTextFieldPreview() {
    Surface() {
        HeadlineTextField(
            headline = remember {
                mutableStateOf("Getting started with Android Jetpack.")
            },
            onHeadlineChange = {}
        )
    }
}

@Preview()
@Composable
fun NotesTextFieldPreview() {
    Surface() {
        NoteTextField(
            note = remember {
                mutableStateOf(
                    "Jetpack encompasses a collection of Android libraries that incorporate best practices and provide backwards compatibility in your Android apps.\n" +
                            "The Jetpack guide to app architecture provides an overview of the best practices and recommended architecture to consider as you build your Android app.\n" +
                            "The following sections cover how you can get started using Jetpack components."
                )
            },
            onNoteChange = {}
        )
    }
}


@Preview
@Composable
fun NotesSettingsRowPreview() {
    NotesSettingsRow()
}