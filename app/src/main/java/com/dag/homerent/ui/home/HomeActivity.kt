package com.dag.homerent.ui.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dag.homerent.base.HomeRentActivity
import com.dag.homerent.composebase.navcontroller.NavGraph
import com.dag.homerent.composebase.navcontroller.NavScreen
import com.dag.homerent.databinding.HomepageActivityBinding
import com.dag.homerent.ui.theme.HomeRentTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : HomeRentActivity<HomeVM,HomepageActivityBinding>() {

    override fun getHomeViewModel(): HomeVM = homeVM

    override fun getLayout(): Int? = null

    @Inject
    lateinit var homeVM: HomeVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeRentTheme {
                NavGraph(
                    startDestination = NavScreen.HomeScreen.route,
                    isOnboard = false
                )
            }
        }
    }
}

@Composable
fun HomeSurface(
    backgroundColor: Color,
    content:@Composable ()->Unit
) {
    HomeRentTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = backgroundColor
        ){
            content()
        }
    }
}

