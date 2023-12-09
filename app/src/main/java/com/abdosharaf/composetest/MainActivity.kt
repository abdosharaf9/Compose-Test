package com.abdosharaf.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF101010)),
                contentAlignment = Alignment.Center
            ) {
                Timer(
                    totalTime = 10L * 1000L,
                    activeColor = Color.Green,
                    inactiveColor = Color.DarkGray,
                    thumbColor = Color(0xFF37B900),
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }
}

@Composable
fun Timer(
    modifier: Modifier = Modifier,
    totalTime: Long,
    activeColor: Color,
    inactiveColor: Color,
    thumbColor: Color,
    strokeWidth: Dp = 5.dp,
    initialValue: Float = 1f
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }
    var isRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = currentTime, key2 = isRunning) {
        if (currentTime > 0 && isRunning) {
            val delayValue = totalTime / 1000L
            delay(delayValue)
            currentTime -= delayValue
            value = currentTime / totalTime.toFloat()
            if (currentTime <= 0) isRunning = false
        }
    }

    Box(modifier = modifier.onSizeChanged { size = it }, contentAlignment = Alignment.Center) {
        Canvas(modifier = modifier) {
            drawArc(
                color = inactiveColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(width = size.width.toFloat(), height = size.height.toFloat()),
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = activeColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(width = size.width.toFloat(), height = size.height.toFloat()),
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            val center = Offset(x = size.width / 2f, y = size.height / 2f)
            val theta = (250f * value + 145f) * (PI / 180f).toFloat()
            val radius = size.width / 2f
            val a = cos(theta) * radius
            val b = sin(theta) * radius
            drawPoints(
                points = listOf(Offset(x = center.x + a, y = center.y + b)),
                pointMode = PointMode.Points,
                color = thumbColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }

        Text(
            text = ceil(currentTime.toDouble().div(1000L)).toLong().toString(),
            color = Color.White,
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isRunning && currentTime > 0L) Color.Red else Color.Green
            ),
            onClick = {
                if (currentTime <= 0L) {
                    currentTime = totalTime
                    isRunning = true
                } else {
                    isRunning = !isRunning
                }
            }) {
            Text(
                text = if (isRunning && currentTime > 0L) "Stop"
                else if (!isRunning && currentTime > 0L) "Start"
                else "Restart",
                color = Color(0xFF101010)
            )
        }
    }
}