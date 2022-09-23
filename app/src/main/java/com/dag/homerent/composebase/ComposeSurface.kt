package com.dag.homerent.composebase

import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dag.homerent.composebase.appbar.CustomAppbar
import com.dag.homerent.ui.theme.HomeRentTheme


@Composable
fun HomeRentSurface(
    appbar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    backgroundColor: Color,
    content: @Composable () -> Unit
) {

    Scaffold(
        topBar = appbar,
        bottomBar = bottomBar,
        backgroundColor = backgroundColor
    ) {
        Surface {
            content()
        }
    }

}

@Composable
fun HomeRentPreview(content: @Composable () -> Unit){
    HomeRentTheme {
        content()
    }
}

@Preview
@Composable
fun HomeRentSurfacePreview() {
    HomeRentSurface(
        appbar = {
            CustomAppbar()
        },
        bottomBar = {
        },
        backgroundColor = Color.LightGray
    ) {
        Text(text = "Deneme")
    }
}