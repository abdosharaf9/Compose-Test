package com.abdosharaf.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
    var count by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedCounter(count = count, textStyle = MaterialTheme.typography.h2)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            count++
        }) {
            Text(text = "Increase the counter", color = Color.White)
        }
    }
}

@Composable
fun AnimatedCounter(count: Int, textStyle: TextStyle, modifier: Modifier = Modifier) {
    var oldCount by remember {
        mutableIntStateOf(count)
    }

    SideEffect {
        oldCount = count
    }

    Row(modifier = modifier) {
        val oldCountString = oldCount.toString()
        val newCountString = count.toString()

        for (i in newCountString.indices) {
            val oldChar = oldCountString.getOrNull(i)
            val newChar = newCountString[i]

            val char = if (oldChar == newChar) {
                oldCountString[i]
            } else {
                newCountString[i]
            }

            AnimatedContent(
                targetState = char,
                label = "",
                transitionSpec = {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                }
            ) { text ->
                Text(
                    text = text.toString(),
                    style = textStyle,
                    softWrap = false
                )
            }
        }
    }
}