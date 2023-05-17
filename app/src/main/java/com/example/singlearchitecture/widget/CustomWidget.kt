package com.example.singlearchitecture.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun CustomWidget(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map {
                it.measure(constraints)
            }
            layout(width = constraints.maxWidth, height = constraints.maxHeight) {
                var xPosition = 0
                placeables.forEach { placeable ->
                    placeable.place(x = xPosition, y = 0)
                    xPosition += placeable.width
                }
            }
        },
        content = content
    )
}

@Preview
@Composable
fun PreviewCustomWidget() {
    CustomWidget(modifier = Modifier.background(color = Color.White)) {
        repeat(50) {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .width(Random.nextInt(50, 200).dp)
                    .background(color = Color(Random.nextLong()))
            ) {

            }
        }
    }
}