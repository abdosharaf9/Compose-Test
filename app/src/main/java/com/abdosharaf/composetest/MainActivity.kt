package com.abdosharaf.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val constraintSet = ConstraintSet {
                val greenBox = createRefFor("greenBox")
                val redBox = createRefFor("redBox")
                val guideline = createGuidelineFromTop(0.25f)

                constrain(ref = greenBox) {
//                    top.linkTo(parent.top)
                    top.linkTo(guideline)
                    bottom.linkTo(guideline)
                    start.linkTo(parent.start)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }

                constrain(ref = redBox) {
//                    top.linkTo(parent.top)
                    top.linkTo(guideline)
                    bottom.linkTo(guideline)
//                    start.linkTo(greenBox.end)
                    end.linkTo(parent.end)
//                    width = Dimension.fillToConstraints
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }

                createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Spread)
            }

            ConstraintLayout(modifier = Modifier.fillMaxSize(), constraintSet = constraintSet) {
                Box(
                    modifier = Modifier
                        .background(Color.Green)
                        .layoutId("greenBox")
                )

                Box(
                    modifier = Modifier
                        .background(Color.Red)
                        .layoutId("redBox")
                )
            }
        }
    }
}