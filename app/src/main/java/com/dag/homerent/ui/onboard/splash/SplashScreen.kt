package com.dag.homerent.ui.onboard.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dag.homerent.composebase.HomeRentPreview
import com.dag.homerent.composebase.navcontroller.NavScreen
import com.dag.homerent.ui.onboard.OnboardSurface

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashVM = viewModel()
) {
    val splashStatus = viewModel.splashState
    LaunchedEffect(Unit) {
        viewModel.readWelcomeState()
    }
    if (splashStatus.welcomeScreenResult.value == WelcomeScreenResult.NotFirstTime) {
        navController.navigate(NavScreen.PhoneScreen.route)
    } else if (splashStatus.welcomeScreenResult.value == WelcomeScreenResult.FirstTime) {
        navController.navigate(NavScreen.WelcomeScreen.route)
    }
    OnboardSurface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Welcome To HomeRent",
                style = MaterialTheme.typography.h1.copy(color = Color.Black)
            )
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    HomeRentPreview {
        SplashScreen(
            rememberNavController(),
        )
    }
}