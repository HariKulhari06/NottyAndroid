package com.hari.notty.feature.category

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hari.notty.core.ui.FilterRow
import com.hari.notty.core.ui.component.NottyBackground
import com.hari.notty.core.ui.theme.*

@Composable
fun CategoryRout(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    CategoryScreen(
        windowSizeClass = windowSizeClass, navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CategoryScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController
) {

    val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(2) }

    NottyBackground {
        Scaffold(topBar = {
            TopAppbar(onClickAddCategory = {})
        }) { innerPadding ->
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding)
                    .fillMaxSize(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item(
                    span = span
                ) {
                    FilterRow(
                        lable = "Categories"
                    )
                }

                items(DEMO_CATEGORIES, key = { it.name }) { category: Category ->
                    CategoryGridItem(
                        modifier = Modifier,
                        containerColor = category.containerColor,
                        iconTint = category.iconTint,
                        name = category.name,
                        countOfNotes = category.notesCount
                    )
                }

                item(
                    span = span
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "You have 4 Categories",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryGridItem(
    modifier: Modifier = Modifier,
    containerColor: Color,
    iconTint: Color,
    name: String,
    countOfNotes: Int
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        colors = CardDefaults.outlinedCardColors(containerColor = containerColor),
        onClick = {}
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.ic_category_folder),
                contentDescription = null,
                tint = iconTint
            )
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = "$countOfNotes notes",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Light)
            )
        }
    }
}

@Preview
@Composable
fun CategoryGridItemPreview() {
    CategoryGridItem(
        name = "Design", countOfNotes = 12, containerColor = NottyLightPink, iconTint = NottyPink
    )
}

@Composable
fun TopAppbar(
    modifier: Modifier = Modifier, onClickAddCategory: () -> Unit
) {
    CenterAlignedTopAppBar(modifier = modifier.statusBarsPadding(), title = {
        Text(
            text = "Categories",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
        )
    }, actions = {
        IconButton(onClick = onClickAddCategory) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_circle),
                contentDescription = null
            )
        }
    })
}


@Preview
@Composable
fun TopAppbarPreview() {
    TopAppbar(
        onClickAddCategory = {},
    )
}

data class Category(
    val name: String, val notesCount: Int, val containerColor: Color, val iconTint: Color
)

val DEMO_CATEGORIES = mutableListOf<Category>(
    Category(
        name = "Design", notesCount = 15, containerColor = NottyLightBlue, iconTint = NottyBlue
    ), Category(
        name = "Success", notesCount = 18, containerColor = NottyLightYellow, iconTint = NottyYellow
    ), Category(
        name = "Scientific",
        notesCount = 150,
        containerColor = NottyLightGreen,
        iconTint = NottyGreen
    ), Category(
        name = "Freelancer", notesCount = 11, containerColor = NottyLightPink, iconTint = NottyPink
    )
)
