package com.sslwireless.androidarch.ui.screens.camera

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.FlipCameraAndroid
import androidx.compose.material.icons.sharp.PhotoLibrary
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sslwireless.androidarch.R

@Composable
fun CameraControls(isDocumentScan: Boolean, cameraUIAction: (CameraUIAction) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isDocumentScan) {
            CameraControl(
                imageVector = Icons.Sharp.FlipCameraAndroid,
                contentDescId = R.string.icn_camera_view_switch_camera_content_description,
                modifier = Modifier.size(35.dp),
                onClick = { cameraUIAction(CameraUIAction.OnSwitchCameraClick) }
            )
        }

        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            CameraControl(
                isDrawable = true,
                painter = painterResource(id = R.drawable.ic_camera_capture),
                contentDescId = R.string.icn_camera_view_camera_shutter_content_description,
                modifier = Modifier
                    .size(70.dp),
                onClick = { cameraUIAction(CameraUIAction.OnCameraClick) }
            )
        }

        if (!isDocumentScan) {
            CameraControl(
                imageVector = Icons.Sharp.PhotoLibrary,
                contentDescId = R.string.icn_camera_view_view_gallery_content_description,
                modifier = Modifier.size(35.dp),
                onClick = {
                    cameraUIAction(CameraUIAction.OnGalleryViewClick) }
            )
        }
    }
}


@Composable
fun CameraControl(
    modifier: Modifier = Modifier,
    isDrawable: Boolean = false,
    imageVector: ImageVector? = null,
    painter: Painter? = null,
    contentDescId: Int,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        if (isDrawable) {
            painter?.let {
                Image(
                    painter = it,
                    contentDescription = stringResource(id = contentDescId),
                    modifier = modifier,
                    contentScale = ContentScale.FillBounds
                )
            }
        } else {
            imageVector?.let {
                Icon(
                    imageVector = it,
                    contentDescription = stringResource(id = contentDescId),
                    tint = Color.White,
                    modifier = modifier
                )
            }
        }
    }
}