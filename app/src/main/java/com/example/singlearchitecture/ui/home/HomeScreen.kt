package com.example.singlearchitecture.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.singlearchitecture.ui.Screen

@Composable
fun HomeScreen(vm: HomeViewModel, navigate: (String) -> Unit) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    HomeContent(uiState = uiState, navigate = navigate)
}

@Composable
fun HomeContent(
    uiState: HomeUiState,
    navigate: (String) -> Unit
) {

    when (uiState) {
        is HomeUiState.Error -> ErrorScreen(message = uiState.message)

        is HomeUiState.Loading -> FullLoadingScreen()

        is HomeUiState.Success -> HomeContentScreen(uiState = uiState, navigate = navigate)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContentScreen(
    uiState: HomeUiState,
    navigate: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        Icons.Rounded.Home,
                        contentDescription = "Logo",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        tint = Color.Magenta
                    )
                    Text(text = "New")
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Fill")

                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = {

                }) {
                    navigate(Screen.Detail.createRoute("Dummy"))
                }
            }
        }
    }
}

@Composable
fun FullLoadingScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Icon(Icons.Rounded.Refresh, tint = Color.Blue, contentDescription = "Error Page")
            Text(text = message)
        }
    }
}

@Preview
@Composable
fun PreviewHomeContent() {
    Surface {
        HomeContent(uiState = HomeUiState.Loading, navigate = {})
    }
}