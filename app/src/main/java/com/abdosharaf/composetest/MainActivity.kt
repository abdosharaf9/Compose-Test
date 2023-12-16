package com.abdosharaf.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.material.Button
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

//    private var i = 0
//    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var text by remember {
                mutableStateOf("")
            }

            /*Button(onClick = { text += "#" }) {
                i++
                Text(text = text)
            }*/

            /*LaunchedEffect(key1 = text) {
                delay(1000L)
                println("The text is $text")
            }*/

            /*LaunchedEffect(key1 = true){
                viewModel.sharedFlow.collect { event ->
                    // Some action
                }
            }*/
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

}

@Composable
fun AnimatedText(counter: Int) {
    val animatable = remember {
        Animatable(initialValue = 0f)
    }
    LaunchedEffect(key1 = counter) {
        animatable.animateTo(targetValue = counter.toFloat())
    }
}

@Composable
fun RememberCoroutine() {
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch {
            delay(1000L)
            println("Hello World!")
        }
    }) {}
}

@Composable
fun RememberUpdateState(onTimeOut: () -> Unit) {
    val updatedOnTimeOut by rememberUpdatedState(newValue = onTimeOut)
    LaunchedEffect(key1 = true) {
        delay(1000L)
        updatedOnTimeOut()
    }
}

@Composable
fun Disposable() {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) println("On pause called")
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun SideEffects() {
    SideEffect {
        println("Called after every successful recompose")
    }
}

@Composable
fun produceStateTest(countUpTo: Int): State<Int> {
    return produceState(initialValue = 0) {
        while (value < countUpTo) {
            delay(1000L)
            value++
        }
    }
}

@Composable
fun DerivedState() {
    var counter by remember {
        mutableIntStateOf(0)
    }
    val counterText by remember {
        derivedStateOf { "The counter is $counter" }
    }
    Button(onClick = { counter++ }) {
        Text(text = counterText)
    }
}

@Composable
fun SnapshotFlow() {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = scaffoldState) {
        snapshotFlow { scaffoldState.snackbarHostState }
            .mapNotNull { it.currentSnackbarData?.message }
            .distinctUntilChanged()
            .collect { message ->
                println("A snackbar with message $message was shown")
            }
    }
}