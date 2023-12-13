package com.abdosharaf.composetest

import android.Manifest
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Preview
@Composable
fun Test() {
    HomeScreen()
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen() {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
        )
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                permissionState.launchMultiplePermissionRequest()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        permissionState.permissions.forEach { perm ->
            when (perm.permission) {
                Manifest.permission.CAMERA -> {
                    val text = when {
                        perm.hasPermission -> {
                            "Camera permission accepted"
                        }

                        perm.shouldShowRationale -> {
                            "Camera permission is needed to access the camera"
                        }

                        perm.isPermanentlyDenied() -> {
                            "Camera permission was permanently denied. You can enable it in the app settings."
                        }

                        else -> {
                            "Camera permission is needed to access the camera"
                        }
                    }
                    Text(text = text, textAlign = TextAlign.Center)
                }

                Manifest.permission.RECORD_AUDIO -> {
                    val text = when {
                        perm.hasPermission -> {
                            "Record audio permission accepted"
                        }

                        perm.shouldShowRationale -> {
                            "Record audio permission is needed to access the microphone"
                        }

                        perm.isPermanentlyDenied() -> {
                            "Record audio permission was permanently denied. You can enable it in the app settings."
                        }

                        else -> {
                            "Record audio permission is needed to access the microphone"
                        }
                    }
                    Text(
                        text = text,
                        modifier = Modifier.padding(top = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.isPermanentlyDenied() =
    !hasPermission && !shouldShowRationale && permissionRequested