package com.abdosharaf.composetest

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
    val moonSpeed = 0.08f
    val midBgSpeed = 0.03f

    val imageHeight = (LocalConfiguration.current.screenWidthDp * (2f / 3f)).dp

    val lazyListState = rememberLazyListState()

    var moonOffset by remember {
        mutableFloatStateOf(0f)
    }
    var midBgOffset by remember {
        mutableFloatStateOf(0f)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y

                val layoutInfo = lazyListState.layoutInfo
                if (lazyListState.firstVisibleItemIndex == 0) {
                    return Offset.Zero
                }
                if (layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1) {
                    return Offset.Zero
                }

                moonOffset += delta * moonSpeed
                midBgOffset += delta * midBgSpeed
                return Offset.Zero
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
        state = lazyListState
    ) {
        items(10) {
            Text(
                text = "Simple Item", modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        item {
            Box(
                modifier = Modifier
                    .clipToBounds()
                    .fillMaxWidth()
                    .height(imageHeight + midBgOffset.toDp())
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFF36B21),
                                Color(0xFFF9A521)
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_moonbg),
                    contentDescription = "moon",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer {
                            translationY = moonOffset
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_midbg),
                    contentDescription = "Mid Background",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer {
                            translationY = midBgOffset
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_outerbg),
                    contentDescription = "Outer Background",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.matchParentSize()
                )
            }
        }

        items(20) {
            Text(
                text = "Simple Item", modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

fun Float.toDp(): Dp = (this / Resources.getSystem().displayMetrics.density).dp