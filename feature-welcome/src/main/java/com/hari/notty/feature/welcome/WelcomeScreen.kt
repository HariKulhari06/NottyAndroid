package com.hari.notty.feature.welcome

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hari.notty.core.ui.LoadingWheel
import com.hari.notty.core.ui.component.NottyBackground
import com.hari.notty.core.ui.component.NottyGradientBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@Composable
fun WelcomeRoute(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope,
    navigateToNextScreen: () -> Unit
) {
    WelcomeScreen(
        windowSizeClass,
        coroutineScope,
        navigateToNextScreen
    )
}

@Composable
fun WelcomeScreen(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope,
    navigateToNextScreen: () -> Unit
) {

    LaunchedEffect(coroutineScope) {
        delay(2000)
        navigateToNextScreen()
    }

    NottyBackground() {
        SplashScreen(
            modifier = Modifier.systemBarsPadding()
        )
    }
}

@Composable
private fun SplashScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(108.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Notty",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = "Easily Manage Your Notes On Your Phone & You Can Have Infinite Notes.",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                textAlign = TextAlign.Center
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            LoadingWheel(contentDesc = "loading wheel")

            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = "Loading...",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }

}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun SplashScreenPreview() {
    NottyBackground {
        SplashScreen()
    }
}
