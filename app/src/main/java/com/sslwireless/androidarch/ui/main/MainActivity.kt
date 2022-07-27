package com.sslwireless.androidarch.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.DestinationsNavHost
import com.sslwireless.androidarch.BuildConfig
import com.sslwireless.androidarch.ui.screens.NavGraphs
import com.sslwireless.androidarch.ui.theme.ArchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArchTheme {
                window?.statusBarColor = MaterialTheme.colors.primary.toArgb()

                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name ${BuildConfig.FLAVOR}!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArchTheme {
        Greeting("Android")
    }
}