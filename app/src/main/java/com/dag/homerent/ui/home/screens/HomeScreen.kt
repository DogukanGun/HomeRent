package com.dag.homerent.ui.home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dag.homerent.R
import com.dag.homerent.composebase.HomeRentPreview
import com.dag.homerent.ui.home.activity.HomeSurface
import com.dag.homerent.ui.theme.HomeFilterButtonBackground
import com.dag.homerent.ui.theme.HomeFilterButtonImageColor
import com.dag.homerent.ui.theme.MainActivityBackground

@Composable
fun HomeScreen(
    viewModel: HomeScreenVM = viewModel()
) {
    val scrollState = rememberScrollState()
    HomeSurface(
        backgroundColor = MainActivityBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(scrollState)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.housing),
                contentDescription = ""
            )
            Column {
                Text(
                    modifier = Modifier.padding(start = 30.dp, bottom = 15.dp),
                    text = stringResource(id = R.string.home_screen_section_1),
                    style = MaterialTheme.typography.h2
                        .copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp, start = 30.dp, end = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HomeRentFilterButton(
                        onClick = {},
                        modifier = Modifier,
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Image(
                                modifier = Modifier.fillMaxWidth(),
                                painter = painterResource(id = R.drawable.ic_baseline_home_work),
                                contentDescription = ""
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Home",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h3
                                    .copy(
                                        fontSize = 8.sp,
                                        color = HomeFilterButtonImageColor
                                    )
                            )
                        }
                    }
                    HomeRentFilterButton(
                        onClick = {},
                        modifier = Modifier,
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Image(
                                modifier = Modifier.fillMaxWidth(),
                                painter = painterResource(id = R.drawable.ic_baseline_home_work),
                                contentDescription = ""
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Home",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h3
                                    .copy(
                                        fontSize = 8.sp,
                                        color = HomeFilterButtonImageColor
                                    )
                            )
                        }
                    }
                    HomeRentFilterButton(
                        onClick = {},
                        modifier = Modifier,
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Image(
                                modifier = Modifier.fillMaxWidth(),
                                painter = painterResource(id = R.drawable.ic_baseline_home_work),
                                contentDescription = ""
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Home",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h3
                                    .copy(
                                        fontSize = 8.sp,
                                        color = HomeFilterButtonImageColor
                                    )
                            )
                        }
                    }
                    HomeRentFilterButton(
                        onClick = {},
                        modifier = Modifier,
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Image(
                                modifier = Modifier.fillMaxWidth(),
                                painter = painterResource(id = R.drawable.ic_baseline_home_work),
                                contentDescription = ""
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Home",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h3
                                    .copy(
                                        fontSize = 8.sp,
                                        color = HomeFilterButtonImageColor
                                    )
                            )
                        }
                    }
                }
            }
            Text(
                modifier = Modifier.padding(start = 30.dp, bottom = 15.dp),
                text = stringResource(id = R.string.home_screen_section_2),
                style = MaterialTheme.typography.h2
                    .copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
            )
            LazyRow(
                modifier = Modifier.padding(bottom = 50.dp)
            ) {
                items(6) { index ->
                    val startDp = if (index == 0) 20.dp else 0.dp
                    HomeScreenCard(
                        modifier = Modifier
                            .padding(start = startDp, end = 20.dp)
                            .size(width = 250.dp, height = 400.dp),
                        imageRes = R.drawable.example_image,
                        homeNameRes = R.string.app_name
                    )
                }
            }
        }

    }
}

@Composable
fun HomeScreenCard(
    modifier: Modifier,
    imageRes: Int,
    homeNameRes: Int
) {

    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(20.dp)
            ),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageRes),
            contentDescription = ""
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 40.dp)
                .offset(y = 150.dp),
            text = stringResource(id = homeNameRes),
            style = MaterialTheme.typography.h1.copy(fontSize = 20.sp)
        )
    }

}

@Composable
fun HomeRentFilterButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    TextButton(
        modifier = modifier
            .background(HomeFilterButtonBackground)
            .size(60.dp),
        onClick = onClick
    ) {
        content()
    }
}

@Composable
@Preview
fun HomeRentFilterButtonPreview() {
    HomeRentPreview {
        HomeRentFilterButton(
            onClick = {},
            modifier = Modifier,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.ic_baseline_home_work),
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Home",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h3.copy(fontSize = 8.sp)
                )
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenCardPreview() {
    HomeRentPreview {
        HomeScreenCard(
            modifier = Modifier
                .size(width = 500.dp, height = 700.dp),
            imageRes = R.drawable.example_image,
            homeNameRes = R.string.app_name
        )
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeRentPreview {
        HomeScreen()
    }
}