package com.dag.homerent.composebase.bottomnavigation


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dag.homerent.composebase.navcontroller.NavScreen
import com.dag.homerent.ui.theme.BackgroundColorVariant
import com.dag.homerent.ui.theme.ButtonColor
import com.dag.homerent.ui.theme.UnSelectedBottomItem


@Composable
fun CustomBottomNavigation(
    currentRoute: String,
    navController:NavHostController
) {
    Box{
        BottomNavigation(
            modifier = Modifier
                .height(40.dp),
            backgroundColor = BackgroundColorVariant
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BarItem.values().forEach {
                    BottomNavigationItem(
                        selected = currentRoute == it.route,
                        unselectedContentColor = UnSelectedBottomItem,
                        selectedContentColor = Color.White,
                        onClick = {
                            if (currentRoute == it.route){
                                return@BottomNavigationItem
                            }else{
                                navController.navigate(it.route){
                                    NavScreen.HomeScreen.route.let {
                                        popUpTo(navController.graph.findStartDestination().id){
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }

                        },
                        icon = {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = it.icon),
                                contentDescription = ""
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun BottomNavigationPreview(){
    CustomBottomNavigation(
        currentRoute = NavScreen.HomeScreen.route,
        navController = rememberNavController()
    )
}


