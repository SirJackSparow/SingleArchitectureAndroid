package com.example.singlearchitecture.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.singlearchitecture.R
import kotlinx.html.body
import kotlinx.html.dom.*
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.title

@Composable
fun DetailScreen(vm: DetailViewModel) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent() {
    Scaffold(topBar = {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
            contentDescription = "BackButton",
            modifier = Modifier.padding(10.dp),
            tint = Color.White
        )
    }, containerColor = Color.LightGray) {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}

fun htmlPages() {
    val mytheme = createHTMLDocument().html {
        head {
            title { +"Kotlin Learning Html" }
        }
        body {
            h1 { + "Nice features" }
        }
    }
}


@Preview
@Composable
fun PreviewDetailScreen() {
    DetailContent()
}