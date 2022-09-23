package com.dag.homerent.composebase.appbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dag.homerent.R
import com.dag.homerent.composebase.HomeRentPreview
import com.dag.homerent.ui.theme.BackgroundColorVariant
import com.dag.homerent.ui.theme.ButtonColor


@Composable
fun CustomAppbar() {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h1
                )
            }
        },
        backgroundColor = BackgroundColorVariant,
        actions = {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_baseline_person
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
@Preview
fun CustomAppbarPreview() {
    HomeRentPreview {
        CustomAppbar()
    }
}