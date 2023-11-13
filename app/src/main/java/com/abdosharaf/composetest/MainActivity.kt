package com.abdosharaf.composetest

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumn {
                itemsIndexed(
                    items = listOf(
                        ListItem(name = "Test 1", image = R.drawable.image_1),
                        ListItem(name = "Test 2", image = R.drawable.image_2),
                        ListItem(name = "Test 3", image = R.drawable.image_3),
                        ListItem(name = "Test 4", image = R.drawable.image_4),
                        ListItem(name = "Test 5", image = R.drawable.image_1),
                        ListItem(name = "Test 6", image = R.drawable.image_2),
                        ListItem(name = "Test 7", image = R.drawable.image_3),
                        ListItem(name = "Test 8", image = R.drawable.image_4),
                        ListItem(name = "Test 9", image = R.drawable.image_1),
                        ListItem(name = "Test 10", image = R.drawable.image_2),
                        ListItem(name = "Test 11", image = R.drawable.image_3),
                        ListItem(name = "Test 12", image = R.drawable.image_4),
                        ListItem(name = "Test 13", image = R.drawable.image_1),
                        ListItem(name = "Test 14", image = R.drawable.image_2),
                        ListItem(name = "Test 15", image = R.drawable.image_3),
                        ListItem(name = "Test 16", image = R.drawable.image_4)
                    )
                ) { index, item ->
                    ListItemComponent(context = this@MainActivity, listItem = item, index = index)
                }

                /*itemsIndexed(
                    items = listOf(
                        "This",
                        "is",
                        "a",
                        "list",
                        "of",
                        "items",
                        "displayed",
                        "using",
                        "LazyColumn",
                        "which",
                        "is",
                        "more",
                        "easier",
                        "than",
                        "recycler",
                        "view",
                        "in",
                        "XML",
                        "and",
                        "its",
                        "adapter"
                    )
                ) { index, item ->
                    Text(
                        text = item,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                }*/

                /*items(count = 5000) {
                    Text(
                        text = "Item $it",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                }*/
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemComponent(
    modifier: Modifier = Modifier,
    context: Context,
    listItem: ListItem,
    index: Int
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        onClick = {
            Toast.makeText(
                context,
                "You clicked ${listItem.name}, at index #$index",
                Toast.LENGTH_SHORT
            ).show()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = listItem.image),
                contentDescription = listItem.name,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color.Transparent, Color.Black)
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = listItem.name,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.tajawal_bold))
                )
            }
        }
    }
}

data class ListItem(
    val name: String,
    val image: Int
)