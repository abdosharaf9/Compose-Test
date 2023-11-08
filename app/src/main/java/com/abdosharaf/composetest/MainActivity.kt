package com.abdosharaf.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ColorBox(modifier = Modifier.fillMaxSize())

            val color = remember {
                mutableStateOf(Color.Red)
            }

            Column(modifier = Modifier.fillMaxSize()) {
                ColorBoxV2(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    color.value = it
                }

                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxSize()
                        .background(color.value)
                )
            }
        }
    }
}

@Composable
fun ColorBox(modifier: Modifier = Modifier) {
    val color = remember {
        mutableStateOf(Color.Yellow)
    }

    Box(modifier = modifier
        .background(color.value)
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            color.value = Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat()
            )
        }, contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Click here to change the color!!",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ColorBoxV2(modifier: Modifier = Modifier, onClick: (Color) -> Unit) {
    Box(modifier = modifier
        .background(Color.Yellow)
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            onClick(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat()
                )
            )
        }, contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Click here to change the color!!",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}