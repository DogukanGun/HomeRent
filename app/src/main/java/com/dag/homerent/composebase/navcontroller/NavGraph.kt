package com.dag.homerent.composebase.navcontroller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dag.homerent.composebase.HomeRentSurface
import com.dag.homerent.composebase.appbar.CustomAppbar
import com.dag.homerent.composebase.bottomnavigation.CustomBottomNavigation
import com.dag.homerent.ui.home.activity.HomeActivity
import com.dag.homerent.ui.home.screens.HomeScreen
import com.dag.homerent.ui.home.screens.HomeScreenVM
import com.dag.homerent.ui.homelist.HomeListScreen
import com.dag.homerent.ui.onboard.OnboardVM
import com.dag.homerent.ui.onboard.register.ui.password.PasswordScreen
import com.dag.homerent.ui.onboard.register.ui.phone.PhoneScreen
import com.dag.homerent.ui.onboard.WelcomeScreen
import com.dag.homerent.ui.onboard.register.ui.password.PasswordVM
import com.dag.homerent.ui.onboard.register.ui.phone.PhoneVM
import com.dag.homerent.ui.onboard.login.ui.LoginUser
import com.dag.homerent.ui.onboard.login.ui.LoginVM
import com.dag.homerent.ui.onboard.register.data.dto.UserRegisterFirstStepInfo

@Composable
fun NavGraph(
    startDestination: String = NavScreen.WelcomeScreen.route,
    isOnboard: Boolean = false
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    HomeRentSurface(
        appbar = {
            if (!isOnboard) {
                CustomAppbar()
            }
        },
        bottomBar = {
            if (!isOnboard) {
                CustomBottomNavigation(
                    currentRoute = currentRoute ?: "",
                    navController = navController
                )
            }
        },
        backgroundColor = Color.LightGray
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavScreen.WelcomeScreen.route) {
                WelcomeScreen(navController = navController)
            }
            composable(NavScreen.PhoneScreen.route) {
                val viewModel = hiltViewModel<PhoneVM>()
                PhoneScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            composable(
                NavScreen.PasswordScreen.route
                    .plus("/{username}")
                    .plus("/{userType}"),
                listOf(
                    navArgument("username") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("userType") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                )
            ) { backStackEntry ->
                val viewModel = hiltViewModel<PasswordVM>()
                val username = backStackEntry.
                                    arguments?.
                                    getString("username") ?: ""
                val userType = backStackEntry.
                                    arguments?.
                                    getString("userType") ?: ""
                PasswordScreen(
                    viewModel = viewModel,
                    userInfo = UserRegisterFirstStepInfo(
                        username = username,
                        userType = userType
                    )
                )
            }
            composable(NavScreen.LoginScreen.route) {
                val viewModel = hiltViewModel<LoginVM>()
                LoginUser(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            composable(NavScreen.HomeScreen.route) {
                val viewModel = hiltViewModel<HomeScreenVM>()
                HomeScreen(
                    viewModel = viewModel
                )
            }
            composable(NavScreen.HomeListScreen.route) {
                HomeListScreen()
            }
            composable(NavScreen.MainActivity.route){
                HomeActivity()
            }
        }
    }
}

fun NavHostController.navigateAndReplaceStartRoute(newHomeRoute: String,popStackRoute:String) {
    popBackStack(popStackRoute, true)
    graph.setStartDestination(newHomeRoute)
    navigate(newHomeRoute)
}