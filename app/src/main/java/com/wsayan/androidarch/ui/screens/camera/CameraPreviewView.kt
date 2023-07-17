package com.wsayan.androidarch.ui.screens.camera

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wsayan.androidarch.R
import androidx.compose.ui.viewinterop.AndroidView
import com.wsayan.androidarch.ui.data.NavigationHeader
import com.wsayan.androidarch.ui.util.buildPreview
import com.wsayan.androidarch.ui.util.getCameraProvider

@Composable
fun CameraPreviewView(
    navigationHeader: NavigationHeader,
    isDocumentScan: Boolean,
    imageCapture: ImageCapture,
    lensFacing: Int = CameraSelector.LENS_FACING_BACK,
    cameraUIAction: (CameraUIAction) -> Unit
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = buildPreview()
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()

    val previewView = remember { PreviewView(context) }

    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
        ) {
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = navigationHeader.title ?: "",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .clickable {
                            cameraUIAction(CameraUIAction.OnCloseClick)
                        }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            AndroidView(
                factory = {
                    previewView
                },
                modifier = Modifier.fillMaxSize()
            ) {

            }
            if (isDocumentScan) {
                Image(
                    painter = painterResource(R.drawable.ic_camera_indicator),
                    contentDescription = "",
                    modifier = Modifier
                        .width(320.dp)
                        .height(190.dp)
                        .align(alignment = Alignment.Center)
                )
            }
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
                    text = navigationHeader.subtitle
                        ?: stringResource(R.string.tap_to_capture_photo),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onPrimary,
                )

                Spacer(modifier = Modifier.height(14.dp))

                CameraControls(isDocumentScan, cameraUIAction)
            }
        }
    }
}