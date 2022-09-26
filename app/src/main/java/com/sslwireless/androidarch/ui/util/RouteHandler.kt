package com.sslwireless.androidarch.ui.util

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sslwireless.androidarch.R
import com.sslwireless.androidarch.ui.data.NavigationHeader
import com.sslwireless.androidarch.ui.screens.camera.CameraScreen
import com.sslwireless.androidarch.ui.screens.destinations.AnimatedSplashScreenDestination
import com.sslwireless.androidarch.ui.screens.destinations.CameraScreenDestination
import com.sslwireless.androidarch.ui.screens.destinations.DonorsScreenDestination
import com.sslwireless.androidarch.ui.screens.destinations.LoginScreenDestination

fun <T> DestinationsNavigator.open(
    route: Int,
    isPopUp: Boolean = false,
    data: T? = null,
) {
    try {
        if (isPopUp) this.popBackStack()

        when (route) {
            R.id.login -> {
                this.navigate(direction = LoginScreenDestination())
            }
            R.id.donors -> {
                this.navigate(direction = DonorsScreenDestination())
            }
            R.id.splash -> {
                this.navigate(direction = AnimatedSplashScreenDestination())
            }
            R.id.donor_details -> {
                this.navigate(direction = DonorsScreenDestination())
            }
            R.id.camera -> {
                val data = data as NavigationHeader
                this.navigate(direction = CameraScreenDestination(data, false))
            }
        }
    } catch (exception: Exception) {
        exception.printStackTrace()
    }
}

fun DestinationsNavigator.logout() {
    this.open<Nothing>(route = R.id.login, isPopUp = true)
}