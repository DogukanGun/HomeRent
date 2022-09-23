package com.dag.homerent.composebase.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.dag.homerent.R
import com.dag.homerent.composebase.navcontroller.NavScreen

enum class BarItem(var title:String,var icon:Int, var route:String){
    HOME_SCREEN("Home Screen",R.drawable.ic_baseline_house,NavScreen.HomeScreen.route),
    PROFILE_SCREEN("List",R.drawable.ic_baseline_map,NavScreen.HomeListScreen.route),
}
