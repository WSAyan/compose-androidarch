package com.wsayan.androidarch.ui.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.wsayan.androidarch.R

@Composable
fun Permission(
    permissions: List<String>,
    rationale: String = "This permission is important for this app. Please grant the permission.",
    permissionNotAvailableContent: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { }
) {
    val permissionsState = rememberMultiplePermissionsState(permissions)

    PermissionsRequired(
        multiplePermissionsState = permissionsState,
        permissionsNotGrantedContent = {
            Rationale(
                text = rationale,
                onRequestPermission = {
                    permissionsState.launchMultiplePermissionRequest()
                }
            )
        },
        permissionsNotAvailableContent = permissionNotAvailableContent,
        content = content
    )
}

@Composable
private fun Rationale(
    text: String,
    onRequestPermission: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(text = stringResource(R.string.reasons_for_permission_request))
        },
        text = {
            Text(text)
        },
        confirmButton = {
            Button(onClick = onRequestPermission) {
                Text(stringResource(R.string.ok))
            }
        }
    )
}

@Composable
fun OpenSettingsPermissionDialog(text: String, context: Context) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(text = stringResource(R.string.open_settings))
        },
        text = {
            Text(text)
        },
        confirmButton = {
            Button(onClick = {
                context.startActivity(
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                    }
                )
            }) {
                Text("Ok")
            }
        }
    )
}
