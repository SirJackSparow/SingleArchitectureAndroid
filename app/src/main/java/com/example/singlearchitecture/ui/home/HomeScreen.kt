package com.example.singlearchitecture.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.singlearchitecture.R
import com.example.singlearchitecture.data.networks.model.UsersRandomModelItem
import com.example.singlearchitecture.ui.theme.Triadic100
import com.example.singlearchitecture.ui.theme.Triadic50
import com.example.singlearchitecture.widget.ButtonFend

@Composable
fun HomeScreen(vm: HomeViewModel, navigate: (String) -> Unit) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    HomeStateScreen(uiState = uiState, navigate = navigate, nextPaging = { vm.nextPage() })
}

@Composable
fun HomeStateScreen(
    uiState: HomeUiState,
    navigate: (String) -> Unit,
    nextPaging: () -> Unit
) {
    when (uiState) {
        is HomeUiState.Loading -> FullLoadingScreen()

        is HomeUiState.Success -> HomeContent(
            navigate = navigate,
            list = uiState.data,
            uiState = uiState,
            nextPaging = nextPaging,
            loadNext = uiState.loadNextPage
        )

        is HomeUiState.Error -> ErrorScreen(message = uiState.message)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    navigate: (String) -> Unit,
    list: List<UsersRandomModelItem>,
    uiState: HomeUiState,
    nextPaging: () -> Unit,
    loadNext: Boolean
) {

    val stateList = rememberLazyListState()

    val isScrollToEnd by remember {
        derivedStateOf {
            stateList.layoutInfo.visibleItemsInfo.lastOrNull()?.index == stateList.layoutInfo.totalItemsCount - 1
        }
    }

    if (isScrollToEnd && loadNext.not()) {
        nextPaging()
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
            LazyColumn(state = stateList) {
                items(list) { userItems ->
                    ItemListUser(userData = userItems)
                }
                if (loadNext) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
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

@Composable
fun ItemListUser(userData: UsersRandomModelItem?) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        val (image, chip, title, description) = createRefs()
        AsyncImage(
            model = userData?.avatar_url ?: "",
            contentDescription = "User Profile Pics",
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                }
                .padding(top = 10.dp)
                .height(70.dp)
                .width(75.dp),
            placeholder = painterResource(R.drawable.ic_launcher_background)
        )

        Text(
            text = userData?.login ?: "No name",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(image.top)
                    start.linkTo(image.end)
                }
                .padding(start = 10.dp, top = 10.dp)
        )

        Text(
            text = "Lorem ipsum draferi for htik queente supreme zaragoca litium do jin fus",
            fontSize = 10.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(title.bottom)
                    start.linkTo(title.start)
                }
                .padding(start = 10.dp, end = 10.dp)
                .size(200.dp, 40.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            color = Color.Gray
        )
        ButtonFend(
            modifier = Modifier
                .width(70.dp)
                .height(30.dp)
                .constrainAs(chip) {
                    top.linkTo(description.bottom)
                    end.linkTo(parent.end)
                },
            type = userData?.type ?: "Users"
        )
    }
}

@Preview
@Composable
fun PreviewHomeContent() {
    Surface {
//        HomeStateScreen(
//            uiState = HomeUiState.Success(data = emptyList(), loadNextPage = true),
//            navigate = {
//            })
        ItemListUser(null)
    }
}
