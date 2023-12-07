package com.abdosharaf.composetest

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF101010))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(16.dp))
                        .padding(30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    var volume by remember {
                        mutableStateOf(0f)
                    }
                    val barsCount = 20

                    MusicKnob(modifier = Modifier.size(90.dp), onValueChanged = { volume = it })

                    Spacer(modifier = Modifier.width(16.dp))

                    VolumeBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        totalCount = barsCount,
                        activeBars = (barsCount * volume).roundToInt()
                    )
                }
            }
        }
    }
}


@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,
    totalCount: Int = 10
) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2f * totalCount)
        }

        Canvas(modifier = modifier) {
            for (i in 0 until totalCount) {
                drawRoundRect(
                    color = if (i > activeBars) Color.DarkGray else Color.Green,
                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitAngle: Float = 25f,
    onValueChanged: (Float) -> Unit
) {
    var rotation by remember {
        mutableStateOf(limitAngle)
    }
    var centerX by remember {
        mutableStateOf(0f)
    }
    var centerY by remember {
        mutableStateOf(0f)
    }
    var touchX by remember {
        mutableStateOf(0f)
    }
    var touchY by remember {
        mutableStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Music Knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val bounds = it.boundsInWindow()
                centerX = bounds.size.width / 2f
                centerY = bounds.size.height / 2f
            }
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitAngle..limitAngle) {
                            val fixedAngle =
                                if (angle in -180f..-limitAngle) angle + 360f else angle
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitAngle) / (360f - 2 * limitAngle)
                            onValueChanged(percent)

                            true
                        } else {
                            false
                        }
                    }

                    else -> false
                }
            }
            .rotate(rotation)
    )
}