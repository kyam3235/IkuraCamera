package jp.kyamlab.ikuracamera

import androidx.compose.runtime.Composable

expect class PermissionManager(callback: PermissionCallback): PermissionHandler

interface PermissionCallback{
    fun onPermissionStatus(permissionType: PermissionType, status: PermissionStatus)
}

@Composable
expect fun createPermissionManager(callback: PermissionCallback): PermissionManager

interface PermissionHandler{
    @Composable
    fun askPermission(permission: PermissionType)

    @Composable
    fun isPermissionGranted(permission: PermissionType): Boolean

    @Composable
    fun launchSettings()
}