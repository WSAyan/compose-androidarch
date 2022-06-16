package com.sslwireless.androidarch.ui.screens.qr

import android.Manifest
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.components.CommonToolbar
import com.sslwireless.androidarch.ui.screens.destinations.ScanQRResultScreenDestination
import com.sslwireless.androidarch.ui.screens.destinations.ScannedDataScreenDestination
import com.sslwireless.androidarch.ui.theme.NipponCANTheme
import com.sslwireless.androidarch.ui.theme.Typography
import com.sslwireless.androidarch.ui.util.BarCodeAnalyser
import com.sslwireless.androidarch.ui.util.OpenSettingsPermissionDialog
import com.sslwireless.androidarch.ui.util.Permission
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Destination
@Composable
fun ScanQRScreen(navigator: DestinationsNavigator? = null) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var preview by remember { mutableStateOf<androidx.camera.core.Preview?>(null) }

    val barCodeVal = remember { mutableStateOf("") }

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
        NipponCANTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
            ) {
                Scaffold(
                    topBar = {
                        CommonToolbar(title = "SCAN QR") {

                            navigator?.navigateUp()
                        }
                    },
                    content = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            AndroidView(
                                factory = { AndroidViewContext ->
                                    PreviewView(AndroidViewContext).apply {
                                        this.scaleType = PreviewView.ScaleType.FILL_CENTER
                                        layoutParams = ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                        )
                                        implementationMode =
                                            PreviewView.ImplementationMode.COMPATIBLE
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxSize(),
                                update = { previewView ->
                                    val cameraSelector: CameraSelector = CameraSelector.Builder()
                                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                                        .build()
                                    val cameraExecutor: ExecutorService =
                                        Executors.newSingleThreadExecutor()
                                    val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> =
                                        ProcessCameraProvider.getInstance(context)

                                    cameraProviderFuture.addListener({
                                        preview =
                                            androidx.camera.core.Preview.Builder().build().also {
                                                it.setSurfaceProvider(previewView.surfaceProvider)
                                            }
                                        val cameraProvider: ProcessCameraProvider =
                                            cameraProviderFuture.get()

                                        val barcodeAnalyser = BarCodeAnalyser { barcodes ->
                                            barcodes.forEach { barcode ->
                                                barcode.rawValue?.let { barcodeValue ->
                                                    barCodeVal.value = barcodeValue

                                                    Toast.makeText(
                                                        context,
                                                        barcodeValue,
                                                        Toast.LENGTH_SHORT
                                                    ).show()

                                                    navigator?.popBackStack()

                                                    navigator?.navigate(
                                                        direction = ScanQRResultScreenDestination(
                                                            true
                                                        )
                                                    )
                                                }
                                            }
                                        }
                                        val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
                                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                            .build()
                                            .also {
                                                it.setAnalyzer(cameraExecutor, barcodeAnalyser)
                                            }

                                        try {
                                            cameraProvider.unbindAll()
                                            cameraProvider.bindToLifecycle(
                                                lifecycleOwner,
                                                cameraSelector,
                                                preview,
                                                imageAnalysis
                                            )
                                        } catch (e: Exception) {
                                            Log.d("TAG", "CameraPreview: ${e.localizedMessage}")
                                        }
                                    }, ContextCompat.getMainExecutor(context))
                                }
                            )
                            Image(
                                painter = painterResource(R.drawable.ic_qr_indicator),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(200.dp)
                                    .align(alignment = Alignment.Center),
                            )
                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .align(Alignment.BottomCenter)
                            ) {
                                Button(
                                    onClick = {
                                        navigator?.navigate(direction = ScannedDataScreenDestination())
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        .padding(horizontal = 15.dp)
                                ) {
                                    Text(text = "SCAN NOW", style = Typography.button)
                                }
                                Spacer(modifier = Modifier.height(50.dp))
                            }

                        }
                    },
                )

            }

        }
    }


}

@Preview
@Composable
fun ScanQRScreenPreview() {
    ScanQRScreen()
}