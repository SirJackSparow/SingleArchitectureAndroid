package com.example.singlearchitecture.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.singlearchitecture.ui.theme.Triadic100
import com.example.singlearchitecture.ui.theme.Triadic50

@Composable
fun RoundedShapes() {
    Shapes(
        small = RoundedCornerShape(percent = 50)
    )
}

@Composable
fun ButtonFend(
    modifier: Modifier = Modifier,
    colorsGradient: List<Color> = listOf(Triadic100, Triadic50),
    type: String
) {
    Surface(
        modifier = modifier.border(
            width = 0.5.dp,
            shape = RoundedCornerShape(20.dp),
            brush = Brush.horizontalGradient(colors = colorsGradient)
        )
    ) {
        Text(
            text = type, textAlign = TextAlign.Center, modifier = Modifier.wrapContentHeight()
        )

    }
}

object CustomButton {

    @Composable
    operator fun invoke(name: String, modifier: Modifier = Modifier) {
        Surface(modifier = modifier, contentColor = Triadic100, shape = RoundedCornerShape(20.dp)) {
            Text(text = name, textAlign = TextAlign.Center, modifier = Modifier.wrapContentHeight())
        }
    }

    @Composable
    fun Filled(name: String, modifier: Modifier = Modifier) {
        Surface(modifier = modifier, color = Triadic100, shape = RoundedCornerShape(20.dp)) {
            Text(
                text = name,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentHeight(),
                style = TextStyle(color = Color.White)
            )
        }
    }
}

@Preview
@Composable
fun PreviewRoundedShapeDesign() {
    CustomButton.Filled(
        name = "Users",
        modifier = Modifier
            .width(100.dp)
            .height(50.dp),
    )
}
