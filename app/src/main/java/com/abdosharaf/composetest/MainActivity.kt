package com.abdosharaf.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    val bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed/*,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy
        )*/
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheet()
        },
        sheetBackgroundColor = Color.Green,
        sheetPeekHeight = 0.dp
    ) {
        MainScreen(bottomSheetState)
    }
}

@Composable
fun BottomSheet() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Bottom Sheet",
            fontSize = 30.sp
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(bottomSheetState: BottomSheetState) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            scope.launch {
                if (bottomSheetState.isCollapsed) {
                    bottomSheetState.expand()
                } else {
                    bottomSheetState.collapse()
                }
            }
        }) {
//            Text(text = "Show/Hide Bottom Sheet")
//            Text(text = "Bottom Sheet Progress ${bottomSheetState.progress}")

            val text = if (bottomSheetState.isCollapsed) {
                "Show Bottom Sheet"
            } else {
                "Hide Bottom Sheet"
            }
            Text(text = text)
        }
    }
}