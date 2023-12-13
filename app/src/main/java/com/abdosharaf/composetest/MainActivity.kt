package com.abdosharaf.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
    var itemsList by remember {
        mutableStateOf(
            (1..20).map {
                ListItem(title = "Item $it")
            }
        )
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(itemsList.size) { i ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        itemsList = itemsList.mapIndexed { index, listItem ->
                            if (index == i) listItem.copy(isSelected = !listItem.isSelected)
                            else listItem
                        }
                    }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = itemsList[i].title)

                if (itemsList[i].isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check, contentDescription = "Selected",
                        tint = Color.Green,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

data class ListItem(
    val title: String,
    var isSelected: Boolean = false
)