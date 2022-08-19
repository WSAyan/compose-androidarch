package com.sslwireless.androidarch.ui.screens.camera

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.data.ImageBody
import com.sslwireless.androidarch.ui.data.NavigationHeader
import com.sslwireless.androidarch.ui.util.OpenSettingsPermissionDialog
import com.sslwireless.androidarch.ui.util.Permission
import kotlinx.coroutines.launch

@Destination
@Composable
fun CameraScreen(
    navigator: DestinationsNavigator? = null,
    resultNavigator: ResultBackNavigator<ImageBody>? = null,
    navigationHeader: NavigationHeader,
    isDocumentScan: Boolean
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    Permission(
        permissions = listOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE),
        rationale = stringResource(R.string.camera_permission_rationale),
        permissionNotAvailableContent = {
            OpenSettingsPermissionDialog(
                text = stringResource(id = R.string.please_enable_camera_permission),
                context = context
            )
        }
    ) {
        CameraView(
            navigationHeader = navigationHeader,
            isDocumentScan = isDocumentScan,
            onImageCaptured = { uri, isGallery ->
                scope.launch {
                    resultNavigator?.navigateBack(
                        ImageBody(
                            id = navigationHeader.id,
                            imageUri = uri
                        )
                    )
                }
            },
            onError = {
                it.printStackTrace()
            },
            onClose = {
                navigator?.navigateUp()
            }
        )
    }

}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun CameraScreenPreview() {
    CameraScreen(navigationHeader = NavigationHeader(), isDocumentScan = false)
}
