package com.abdosharaf.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val windowInfo = rememberWindowInfo()

    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        CompactDesign()
    } else {
        MediumDesign()
    }
}

@Composable
fun CompactDesign() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(20) {
            Text(
                text = "Item ${it + 1}",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
                    .padding(8.dp)
            )
        }

        items(20) {
            Text(
                text = "Item ${it + 11}",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun MediumDesign() {
    Row(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(20) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green)
                        .padding(8.dp)
                )
            }
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(20) {
                Text(
                    text = "Item ${it + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Cyan)
                        .padding(8.dp)
                )
            }
        }
    }
}