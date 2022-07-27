package com.sslwireless.androidarch.ui.screens.camera

import android.Manifest
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.data.ImageBody
import com.sslwireless.androidarch.ui.data.NavigationHeader
import com.sslwireless.androidarch.ui.theme.ArchTheme
import com.sslwireless.androidarch.ui.theme.Typography
import com.sslwireless.androidarch.ui.util.*
import kotlinx.coroutines.launch

@Destination
@Composable
fun CameraScreen(
    navigator: DestinationsNavigator? = null,
    resultNavigator: ResultBackNavigator<ImageBody>? = null,
    navigationHeader: NavigationHeader
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    var previewUseCase by remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
    val imageCaptureUseCase by remember {
        mutableStateOf(
            ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .build()
        )
    }

    val isCaptureClicked = remember {
        mutableStateOf(false)
    }

    Permission(
        permission = Manifest.permission.CAMERA,
        rationale = "Camera permission required for verification purposes.",
        permissionNotAvailableContent = {
            OpenSettingsPermissionDialog(
                text = "Please enable camera permission!",
                context = context
            )
        }
    ) {
        ArchTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.primary)
                ) {
                    Row(
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 18.dp)
                    ) {
                        Text(
                            text = navigationHeader.title ?: "Capture photo",
                            style = Typography.body1,
                            color = MaterialTheme.colors.onPrimary,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically)
                                .clickable {
                                    navigator?.navigateUp()
                                }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    CameraPreview(
                        modifier = Modifier
                            .fillMaxSize(),
                        onUseCase = {
                            previewUseCase = it
                        }
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_camera_indicator),
                        contentDescription = "",
                        modifier = Modifier
                            .width(320.dp)
                            .height(190.dp)
                            .align(alignment = Alignment.Center)
                    )
                }
                Column(
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.primary),

                    ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(vertical = 30.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = navigationHeader.subtitle ?: "Tap to capture",
                            style = Typography.caption,
                            color = MaterialTheme.colors.onPrimary,
                        )
                        Spacer(modifier = Modifier.height(14.dp))

                        Image(
                            modifier = Modifier
                                .size(70.dp)
                                .clickable {
                                    if (isCaptureClicked.value) return@clickable

                                    isCaptureClicked.value = true

                                    coroutineScope.launch {
                                        imageCaptureUseCase
                                            .takePicture(context.executor)
                                            .apply {
                                                resultNavigator?.navigateBack(
                                                    ImageBody(
                                                        id = navigationHeader.id,
                                                        imageUri = this.toUri()
                                                    )
                                                )
                                            }
                                    }
                                },
                            painter = painterResource(R.drawable.ic_camera_capture),
                            contentDescription = "content description",
                        )
                    }
                }

            }
        }

        LaunchedEffect(previewUseCase) {
            val cameraProvider = context.getCameraProvider()
            try {
                // Must unbind the use-cases before rebinding them.
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    previewUseCase,
                    imageCaptureUseCase
                )
            } catch (ex: Exception) {
                Log.e("CameraCapture", "Failed to bind camera use cases", ex)
            }
        }
    }

}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
    onUseCase: (UseCase) -> Unit = { }
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val previewView = PreviewView(context).apply {
                this.scaleType = scaleType
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
            onUseCase(
                Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
            )
            previewView
        }
    )
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun CameraScreenPreview() {
    CameraScreen(navigationHeader = NavigationHeader())
}
