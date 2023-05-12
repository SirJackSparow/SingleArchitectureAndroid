package com.example.singlearchitecture.widget

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RoundedShapes() {
    Shapes(
        small = RoundedCornerShape(percent = 50)
    )
}

@Preview
@Composable
fun PreviewRoundedShapeDesign() {

}
