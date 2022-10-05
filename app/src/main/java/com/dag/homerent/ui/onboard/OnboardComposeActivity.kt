package com.dag.homerent.ui.onboard

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dag.homerent.R
import com.dag.homerent.composebase.navcontroller.NavGraph
import com.dag.homerent.composebase.navcontroller.NavScreen
import com.dag.homerent.localdatastorage.preferencesdatastore.PreferencesDataStore
import com.dag.homerent.ui.theme.Background
import com.dag.homerent.ui.theme.HomeRentTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardComposeActivity : ComponentActivity() {

    @Inject
    lateinit var onboardVM: OnboardVM

    var onboardActive = true

    @Inject
    lateinit var preferencesDataStore: PreferencesDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeRentTheme {
                NavGraph(
                    startDestination = NavScreen.SplashScreen.route,
                    isOnboard = onboardActive,
                    preferencesDataStore = preferencesDataStore
                )
            }
        }
    }
}


@Composable
fun OnboardSurface(
    content:@Composable ()->Unit
) {
    HomeRentTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Background),
            color = Color.Transparent
        ){
            content()
        }
    }
}

@Composable
fun OnboardTitle(
    title:String = stringResource(id = R.string.phone_screen_title),
    subtitle:String = stringResource(id = R.string.phone_screen_text)
){
    Text(
        modifier = Modifier.padding(bottom = 8.dp, top = 100.dp),
        text = title,
        style = MaterialTheme.typography.h1
    )
    Text(
        modifier = Modifier.padding(bottom = 220.dp),
        text = subtitle,
        style = MaterialTheme.typography.h3
    )
}

fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}