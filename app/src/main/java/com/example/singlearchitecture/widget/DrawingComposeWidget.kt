package com.example.singlearchitecture.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DrawingCompose() {
    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
        drawCircle(
            Color.Cyan,
            center = Offset(
                x = 100.dp.toPx(),
                y = 200.dp.toPx()
            ),
            radius = 60.dp.toPx()
        )
    })
}

@Composable
fun DrawingScaleCompose() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        scale(scaleX = 5f, scaleY = 15f) {
            drawCircle(Color.Cyan, radius = 20.dp.toPx())
        }
    }
}

@Composable
fun GraphyctLineCompose() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier
            .padding(8.dp)
            .aspectRatio(3 / 2f)
            .fillMaxSize()) {
            val barWidthPx = 1.dp.toPx()
            drawRect(Color.DarkGray, style = Stroke(barWidthPx))
        }
    }
}

@Preview
@Composable
fun PreviewDrawingCompose() {
    Surface() {
        GraphyctLineCompose()
    }
}