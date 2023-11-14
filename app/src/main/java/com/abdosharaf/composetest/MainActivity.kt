package com.abdosharaf.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var boxSizeState by remember {
                mutableStateOf(200.dp)
            }

            val size by animateDpAsState(
                targetValue = boxSizeState,
                /*tween(
                    durationMillis = 3000,
                    delayMillis = 0,
                    easing = FastOutSlowInEasing
                )*/

                /*spring(
                    dampingRatio = Spring.DampingRatioHighBouncy
                )*/

                /*keyframes {
                    durationMillis = 3000
                    boxSizeState at 0 with LinearEasing
                    boxSizeState * 1.5f at 1000 with FastOutSlowInEasing
                    boxSizeState * 2f at 2000 with FastOutLinearInEasing
                    boxSizeState * 3f at 3000 with LinearOutSlowInEasing
                }*/
                tween(
                    durationMillis = 1500,
                    delayMillis = 100
                ), label = ""
            )

            val infiniteTransition = rememberInfiniteTransition(label = "")
            val boxColor by infiniteTransition.animateColor(
                initialValue = Color.Green,
                targetValue = Color.Red,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 3000
                    ),
                    repeatMode = RepeatMode.Reverse
                ), label = ""
            )

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(size)
                        .background(boxColor),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                        boxSizeState += 50.dp
                    }) {
                        Text(text = "Increase Box Size")
                    }
                }
            }
        }
    }
}