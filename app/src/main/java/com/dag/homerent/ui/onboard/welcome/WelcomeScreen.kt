package com.dag.homerent.ui.onboard.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dag.homerent.R
import com.dag.homerent.composebase.HomeRentPreview
import com.dag.homerent.composebase.button.CustomButton
import com.dag.homerent.composebase.navcontroller.NavScreen
import com.dag.homerent.ui.onboard.OnboardSurface


@Composable
fun WelcomeScreen(
    navController: NavHostController,
    viewModel: WelcomeVM = viewModel()
) {
    val state = viewModel.welcomeState
    OnboardSurface {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.welcome_screen_title),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(bottom = 22.dp, start = 20.dp)
            )
            Text(
                text = stringResource(id = R.string.welcome_screen_subtitle),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(bottom = 68.dp, start = 20.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_homerentonboard),
                contentDescription = "Home",
                modifier = Modifier.padding(bottom = 70.dp)
            )
            CustomButton(
                modifier = Modifier
                    .padding(bottom = 22.dp, start = 20.dp)
                    .fillMaxWidth(fraction = 0.55f)
                    .height(60.dp),
                onClick = {
                    navController.navigate(NavScreen.PhoneScreen.route)
                },
                border = BorderStroke(1.dp, Color.Black),
                color = MaterialTheme.colors.primary
            ) {
                Text(
                    text = stringResource(id = R.string.welcome_screen_button_text),
                    color = Color.White,
                    style = MaterialTheme.typography.body1
                )
            }
            Text(
                text = stringResource(id = R.string.welcome_screen_subtitle2),
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}


@Composable
@Preview
fun WelcomeScreenPreview() {
    HomeRentPreview {
        WelcomeScreen(
            rememberNavController(),
        )
    }
}