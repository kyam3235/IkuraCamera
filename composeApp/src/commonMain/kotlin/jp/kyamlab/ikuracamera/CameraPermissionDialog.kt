package jp.kyamlab.ikuracamera

import androidx.compose.runtime.Composable

@Composable
expect fun CameraPermissionDialog(
    onDismissRequest: () -> Unit,
)
