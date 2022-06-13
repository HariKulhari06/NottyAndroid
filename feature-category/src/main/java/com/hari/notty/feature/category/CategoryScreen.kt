package com.hari.notty.feature.category

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hari.notty.core.ui.component.NottyBackground

@Composable
fun CategoryRout(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {

    CategoryScreen(
        windowSizeClass = windowSizeClass,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CategoryScreen(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController
) {
    NottyBackground {
        Scaffold(
            topBar = {
                TopAppbar(
                    onClickAddCategory = {}
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .consumedWindowInsets(innerPadding)
                    .fillMaxSize()
            ) {

            }
        }
    }
}

@Composable
fun TopAppbar(
    modifier: Modifier = Modifier,
    onClickAddCategory: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier.statusBarsPadding(),
        title = {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
            )
        },
        actions = {
            IconButton(onClick = onClickAddCategory) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_circle),
                    contentDescription = null
                )
            }
        }
    )
}


@Preview
@Composable
fun TopAppbarPreview() {
    TopAppbar(
        onClickAddCategory = {},
    )
}
