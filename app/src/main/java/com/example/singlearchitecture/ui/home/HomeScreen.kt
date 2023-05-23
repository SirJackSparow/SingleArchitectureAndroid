package com.example.singlearchitecture.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.singlearchitecture.data.networks.model.UsersRandomModelItem
import com.example.singlearchitecture.ui.Screen
import com.example.singlearchitecture.ui.theme.Triadic100
import com.example.singlearchitecture.ui.theme.Triadic50

@Composable
fun HomeScreen(vm: HomeViewModel, navigate: (String) -> Unit) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    HomeStateScreen(uiState = uiState, navigate = navigate)
}

@Composable
fun HomeStateScreen(
    uiState: HomeUiState,
    navigate: (String) -> Unit
) {
    when (uiState) {
        is HomeUiState.Loading -> FullLoadingScreen()

        is HomeUiState.Success -> HomeContent(navigate = navigate, list = uiState.data)

        is HomeUiState.Error -> ErrorScreen(message = uiState.message)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    navigate: (String) -> Unit,
    list: List<UsersRandomModelItem>
) {

    val stateList = rememberLazyListState()
    val isScrollToEnd by remember {
        derivedStateOf {
            stateList.layoutInfo.visibleItemsInfo.lastOrNull()?.index == stateList.layoutInfo.totalItemsCount - 1
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column() {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 15.sp
                        )
                    ) {
                        append("Welcome to Git Api ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.LightGray,
                            fontWeight = FontWeight.ExtraBold
                        )
                    ) {
                        append("Version 1")
                    }
                },
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            Card(
                elevation = CardDefaults.elevatedCardElevation(pressedElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(150.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Triadic100, Triadic50
                                )
                            )
                        )
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Single Architecture Android Compose",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(20.dp)
                    )
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
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
        HomeStateScreen(
            uiState = HomeUiState.Success(data = emptyList(), loadNextPage = true),
            navigate = {
            })
    }
}
