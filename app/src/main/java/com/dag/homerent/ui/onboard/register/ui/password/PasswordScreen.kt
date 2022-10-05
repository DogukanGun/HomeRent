package com.dag.homerent.ui.onboard.register.ui.password

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dag.homerent.R
import com.dag.homerent.composebase.HomeRentPreview
import com.dag.homerent.ui.home.HomeActivity
import com.dag.homerent.ui.onboard.OnboardSurface
import com.dag.homerent.ui.onboard.OnboardTitle
import com.dag.homerent.ui.onboard.findActivity
import com.dag.homerent.ui.onboard.register.data.dto.UserRegisterFirstStepInfo
import com.dag.homerent.ui.onboard.register.data.request.RegisterUserModel
import com.dag.homerent.ui.onboard.register.ui.phone.InputWrapper
import com.dag.homerent.ui.onboard.register.ui.phone.NumberInput


@Composable
fun PasswordScreen(
    viewModel: PasswordVM = viewModel(),
    userInfo:UserRegisterFirstStepInfo
) {
    val state = viewModel.passwordState
    var password by remember { mutableStateOf("") }
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
            Text(
                text = stringResource(id = R.string.password_screen_subtitle),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 30.dp)
            )
            InputWrapper(
                textTitle = R.string.password_screen_input_title,
                textStyle = MaterialTheme.typography.h2,
                textModifier = Modifier.padding(top = 30.dp, start = 20.dp),
                modifier = Modifier
                    .size(width = 400.dp, height = 150.dp)
                    .padding(start = 30.dp, end = 30.dp)
            ) {
                NumberInput(
                    onValueChange = {
                        password = it
                    },
                    password = true,
                    modifier = Modifier.padding(top = 14.dp, start = 15.dp, end = 15.dp),
                    leadingIcon = null,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_right_alt),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.clickable {
                                val userModel = RegisterUserModel(
                                    username = userInfo.username,
                                    password = password,
                                )
                                viewModel.createUser(userModel,userInfo.userType)
                            }
                        )
                    }
                )
            }
            Text(
                text = stringResource(id = R.string.password_screen_timer),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 50.dp)
            )

        }
    }
}

@Composable
@Preview
fun PasswordScreenPreview() {
    HomeRentPreview {
        PasswordScreen(
            userInfo = UserRegisterFirstStepInfo("",""),
        )
    }
}