package jp.kyamlab.ikuracamera

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import platform.AVFoundation.AVAuthorizationStatusAuthorized
import platform.AVFoundation.AVAuthorizationStatusDenied
import platform.AVFoundation.AVAuthorizationStatusNotDetermined
import platform.AVFoundation.AVAuthorizationStatusRestricted
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.authorizationStatusForMediaType
import platform.AVFoundation.requestAccessForMediaType

@Composable
actual fun CameraPermissionDialog(
    onDismissRequest: () -> Unit,
) {
    val cameraPermissionStatus = AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo)
    when (cameraPermissionStatus) {
        AVAuthorizationStatusAuthorized -> {
            return
        }

        AVAuthorizationStatusNotDetermined -> {
            AVCaptureDevice.requestAccessForMediaType(AVMediaTypeVideo) {
                return@requestAccessForMediaType
            }
        }

        AVAuthorizationStatusDenied, AVAuthorizationStatusRestricted -> {
            AlertDialog(
                onDismissRequest = { },
                title = { Text(text = "Permission request") },
                text = {
                    Text(
                        text =
                            "Camera access denied. Please grant camera permission to take photos."
                    )
                },
                confirmButton = {
                    TextButton(onClick = onDismissRequest) {
                        Text(text = "Setting permission")
                    }
                }
            )
        }

        else -> {
            AlertDialog(
                onDismissRequest = { },
                title = { Text(text = "Permission request") },
                text = { Text(text = "Camera permission not available.") },
                confirmButton = {
                    TextButton(onClick = onDismissRequest) {
                        Text(text = "Setting permission")
                    }
                }
            )
        }
    }
}
