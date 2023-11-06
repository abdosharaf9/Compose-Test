package com.abdosharaf.composetest

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test(this)
        }
    }
}

@Composable
fun Test(context: Context) {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxHeight(0.7f)
            .fillMaxWidth()
            .border(5.dp, Color.Red)
            .padding(16.dp)
            .border(5.dp, Color.Yellow)
            .padding(16.dp)
            .border(5.dp, Color.Blue)
            .padding(16.dp)
            .border(5.dp, Color.Black)
            .padding(16.dp)
//            .width(700.dp)
//            .requiredWidth(700.dp)
//            .padding(vertical = 20.dp, horizontal = 25.dp)
//            .padding(top = 50.dp, start = 20.dp, end = 30.dp, bottom = 60.dp)
    ) {
//        Text(text = "Abdo", modifier = Modifier.offset(0.dp, 100.dp))
        Text(
            text = "Abdo", modifier = Modifier
                .border(5.dp, Color.Magenta)
                .offset(20.dp, 20.dp)
                .border(10.dp, Color.Gray)
                .padding(40.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Sharaf", modifier = Modifier.clickable {
            Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show()
        })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
//    Test()
}