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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
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
fun GraphictLineCompose() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(3 / 2f)
                .fillMaxSize()
                .drawWithCache {
                    onDrawBehind {

                    }
                }
        ) {
            val barWidthPx = 1.dp.toPx()
            drawRect(Color.DarkGray, style = Stroke(barWidthPx))

            //vertical
            val verticalLines = 4
            val verticalSize = size.width / (verticalLines + 1)
            repeat(verticalLines) { i ->
                val startX = verticalSize * (i + 1)
                drawLine(
                    Color.DarkGray,
                    start = Offset(startX, 0f),
                    end = Offset(startX, size.height),
                    strokeWidth = barWidthPx
                )
            }

            //horizontal
            val horizontalLines = 3
            val sectionSize = size.height / (horizontalLines + 1)
            repeat(horizontalLines) { i ->
                val startY = sectionSize * (i + 1)
                drawLine(
                    Color.DarkGray,
                    start = Offset(0f, startY),
                    end = Offset(size.width, startY),
                    strokeWidth = barWidthPx
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewDrawingCompose() {
    Surface() {
        GraphictLineCompose()
    }
}