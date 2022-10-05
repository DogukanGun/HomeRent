package com.dag.homerent.ui.onboard.login.ui

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dag.homerent.R
import com.dag.homerent.composebase.HomeRentPreview
import com.dag.homerent.composebase.button.CustomButton
import com.dag.homerent.ui.home.HomeActivity
import com.dag.homerent.ui.onboard.OnboardSurface
import com.dag.homerent.ui.onboard.OnboardTitle
import com.dag.homerent.ui.onboard.findActivity
import com.dag.homerent.ui.onboard.login.data.request.LoginRequest
import com.dag.homerent.ui.onboard.register.ui.phone.NumberInput

@Composable
fun LoginUser(
    viewModel: LoginVM = viewModel(),
    navController: NavHostController,
){
    val state = viewModel.loginState
    var username by remember { mutableStateOf("") }
    var passwordNumber by remember { mutableStateOf("") }

    if (state.success.value){
        val activity = LocalContext.current.findActivity()
        activity?.startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()
    }

    OnboardSurface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            OnboardTitle()
            NumberInput(
                onValueChange = {
                    username = it
                },
                modifier = Modifier.padding(top = 100.dp, start = 15.dp, end = 15.dp),
                leadingIcon = {},
                trailingIcon = {},
                label = stringResource(id = R.string.register_screen_phone_input_placeholder)
            )
            NumberInput(
                onValueChange = {
                    passwordNumber = it
                },
                password = true,
                modifier = Modifier
                    .padding(top = 14.dp, start = 15.dp, end = 15.dp),
                leadingIcon = {},
                trailingIcon = {},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword),
                label = stringResource(id = R.string.register_screen_password_input_placeholder)
            )
            CustomButton(
                modifier = Modifier
                    .padding(top = 90.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 30.dp, end = 30.dp),
                onClick = {
                    val loginModel = LoginRequest(
                        username = username,
                        password = passwordNumber
                    )
                    viewModel.loginUser(loginModel)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.register_screen_password_button_text),
                    style = MaterialTheme.typography.h2,
                )
            }
        }

    }
}

@Composable
@Preview
fun LoginUserPreview(){
    HomeRentPreview {
        LoginUser(
            navController = rememberNavController()
        )
    }
}
