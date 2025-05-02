package jp.kyamlab.ikuracamera

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
actual fun CameraPermissionDialog(
    onDismissRequest: () -> Unit,
) {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    if (!cameraPermissionState.status.isGranted) {
        val rationalMessage = if (cameraPermissionState.status.shouldShowRationale) {
            "Camera permission is required to take photos"
        } else {
            "Camera not available"
        }
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = "Permission request") },
            text = { Text(text = rationalMessage) },
            confirmButton = {
                TextButton(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                    Text(text = "Setting permission")
                }
            }
        )
    }
}
