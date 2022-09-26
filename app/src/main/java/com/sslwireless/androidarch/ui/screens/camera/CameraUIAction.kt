package com.sslwireless.androidarch.ui.screens.camera

sealed class CameraUIAction {
    object OnCameraClick : CameraUIAction()
    object OnGalleryViewClick : CameraUIAction()
    object OnSwitchCameraClick : CameraUIAction()
    object OnCloseClick : CameraUIAction()
    object OnFlashClick : CameraUIAction()
}