package com.abdosharaf.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tajwalFontFamily = FontFamily(
            Font(resId = R.font.tajawal_light, weight = FontWeight.Light),
            Font(resId = R.font.tajawal_medium, weight = FontWeight.Medium),
            Font(resId = R.font.tajawal_regular, weight = FontWeight.Normal),
            Font(resId = R.font.tajawal_bold, weight = FontWeight.Bold),
        )

        setContent {
            Box(
                modifier = Modifier
                    .background(Color(0xFF101010))
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            color = Color.Blue,
                            fontSize = 40.sp
                        )) {
                            append("A")
                        }

                        append("bdo")

                        withStyle(style = SpanStyle(
                            color = Color.Blue,
                            fontSize = 40.sp
                        )) {
                            append("S")
                        }

                        append("haraf")
                    },
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = tajwalFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}