package com.sslwireless.androidarch.ui.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.network.data.resources.BloodGroup
import com.sslwireless.androidarch.network.data.resources.ResourcesResponse
import com.sslwireless.androidarch.ui.base.BaseComponent
import com.sslwireless.androidarch.ui.base.UIState
import com.sslwireless.androidarch.ui.components.ProgressBarHandler
import com.sslwireless.androidarch.ui.components.fadeInAnimation
import com.sslwireless.androidarch.ui.theme.DuskBlue
import com.sslwireless.androidarch.ui.util.open
import com.sslwireless.androidarch.ui.util.showToast
import kotlinx.coroutines.delay

@RootNavGraph(start = true)
@Destination
@Composable
fun AnimatedSplashScreen(navigator: DestinationsNavigator? = null) {
    val splashViewModel: SplashViewModel = hiltViewModel()
    var startAnimation by remember { mutableStateOf(false) }
    val animationTimeout = 3000
    val context = LocalContext.current
    val alphaAnim = fadeInAnimation(startAnimation = startAnimation, timeout = animationTimeout)

    var data: List<BloodGroup>? by remember { mutableStateOf(null) }

    LaunchedEffect(key1 = true) {
        startAnimation = true

        delay(animationTimeout.toLong())

        splashViewModel.getResources()

        splashViewModel.uiState.collect {
            when (it) {
                is UIState.Loading -> {

                }
                is UIState.DataLoaded -> {
                    val resourcesResponse = it.data as ResourcesResponse

                    data = resourcesResponse.data?.blood_groups

                    context.showToast("${data?.size}")

                    navigator?.open<Any>(route = R.id.login, isPopUp = true)
                }
                is UIState.Error -> {
                    context.showToast(it.message)
                }
                else -> {

                }
            }
        }
    }

    BaseComponent(
        backgroundColor = if (isSystemInDarkTheme()) Color.Black else DuskBlue,
        progressBarState = splashViewModel.showProgressBar.collectAsState(),
        unauthorizedState = splashViewModel.unauthorized.collectAsState(),
        progressBarContent = {
            ProgressBarHandler(show = it)
        },
        unAuthorizedContent = {

        }
    ) {
        Splash(alpha = alphaAnim.value)
    }
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else DuskBlue)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
            painter = painterResource(R.drawable.logo_1),
            contentDescription = "content description"
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview() {
    Splash(alpha = 1f)
}
