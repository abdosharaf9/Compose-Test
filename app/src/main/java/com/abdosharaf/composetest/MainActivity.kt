package com.abdosharaf.composetest

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

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

@Composable
fun HomeScreen() {
    AndroidView(factory = { TextView(it) }) { tvTest ->
        tvTest.apply {
            text = "Hello using XML!!"
            setTextColor(Color.BLACK)
            textSize = 20f
            gravity = Gravity.CENTER
        }
    }
}