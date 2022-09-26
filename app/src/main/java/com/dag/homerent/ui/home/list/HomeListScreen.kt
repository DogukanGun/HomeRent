package com.dag.homerent.ui.home.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dag.homerent.R
import com.dag.homerent.composebase.HomeRentPreview
import com.dag.homerent.ui.home.HomeSurface
import com.dag.homerent.ui.theme.HomeListRowButtonColor
import com.dag.homerent.ui.theme.HomeListRowColor
import com.dag.homerent.ui.theme.MainActivityBackground

@Composable
fun HomeListScreen(

){
    HomeSurface(
        backgroundColor = MainActivityBackground
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp)
        ){
            items(count = 15){
                HomeListRow(
                    modifier = Modifier
                        .size(width = 400.dp, height = 200.dp)
                        .padding(bottom = 25.dp),
                    likedButtonClicked = {},
                    callButtonClicked = {},
                    messageButtonClicked = {}
                )
            }
        }
    }
}

@Composable
fun HomeListRow(
    modifier: Modifier,
    likedButtonClicked: () -> Unit,
    messageButtonClicked: () -> Unit,
    callButtonClicked: () -> Unit
){
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier
                .clip(AbsoluteRoundedCornerShape(
                    topLeft = 20.dp,
                    bottomLeft = 20.dp
                )),
            painter = painterResource(id = R.drawable.example_image),
            contentDescription = ""
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .padding(top = 40.dp, start = 30.dp)
            ) {
                Text(
                    text = "Calobasas Apartment",
                    style = MaterialTheme.typography.h2
                        .copy(Color.Black, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "\$8000",
                    style = MaterialTheme.typography.h2.copy(Color.Black)
                )
            }
            TextButton(
                onClick = { likedButtonClicked() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = ""
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(HomeListRowColor)
                    .align(Alignment.BottomEnd),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tedy",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(start = 15.dp)
                )
                Row(
                ){
                    OutlinedButton(
                        onClick = { callButtonClicked() },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = HomeListRowButtonColor,
                        ),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Call,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    OutlinedButton(
                        onClick = { messageButtonClicked() },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                        ),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Message,
                            contentDescription = "",
                            tint = HomeListRowButtonColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun HomeListRowPreview(){
    HomeRentPreview{
        HomeListRow(
            modifier = Modifier
                .size(width = 400.dp, height = 200.dp),
            likedButtonClicked = {},
            callButtonClicked = {},
            messageButtonClicked = {}
        )
    }
}

@Composable
@Preview
fun HomeListScreenPreview(){
    HomeRentPreview {
        HomeListScreen()
    }
}