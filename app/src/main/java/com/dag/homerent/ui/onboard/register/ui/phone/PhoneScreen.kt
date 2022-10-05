package com.dag.homerent.ui.onboard.register.ui.phone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dag.homerent.R
import com.dag.homerent.composebase.HomeRentPreview
import com.dag.homerent.composebase.navcontroller.NavScreen
import com.dag.homerent.composebase.navcontroller.addData
import com.dag.homerent.composebase.radiobutton.CustomRadioButton
import com.dag.homerent.ui.onboard.OnboardSurface
import com.dag.homerent.ui.onboard.OnboardTitle
import com.dag.homerent.ui.theme.BackgroundColor
import com.dag.homerent.ui.theme.BackgroundColorVariant

@Composable
fun PhoneScreen(
    navController: NavHostController,
    viewModel: PhoneVM = viewModel()
) {
    val state = viewModel.phoneState
    if (state.success.value) {
        navController.navigate(
            NavScreen.PasswordScreen.addData(
                "/${state.username}/${state.userType.uppercase()}"
            ),
        )
        state.success.value = false
    }
    OnboardSurface {
        if (state.error.value != null) {
            WrongInputMessage {
                state.error.value = null
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                OnboardTitle()
                PhoneScreenTypeButtons{
                    state.userType = viewModel.getUserType(it)
                }
                Spacer(modifier = Modifier.size(60.dp))
                InputWrapper(
                    textTitle = R.string.phone_screen_phone_input_title,
                    textStyle = MaterialTheme.typography.h2,
                    textModifier = Modifier.padding(top = 30.dp, start = 20.dp),
                    modifier = Modifier
                        .size(width = 400.dp, height = 150.dp)
                        .padding(start = 30.dp, end = 30.dp)
                ) {
                    NumberInput(
                        onValueChange = {
                            state.username = it
                        },
                        modifier = Modifier.padding(top = 14.dp, start = 15.dp, end = 15.dp),
                        leadingIcon = {
                            Image(
                                ImageBitmap.imageResource(id = R.drawable.germany),
                                modifier = Modifier.size(40.dp),
                                contentDescription = ""
                            )
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_arrow_right_alt),
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.clickable {
                                    viewModel.validatePhone()
                                }
                            )
                        },
                    )
                }
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, top = 36.dp),
                    onClick = {
                        navController.navigate(NavScreen.LoginScreen.route)
                    },
                ) {
                    Text(
                        text = stringResource(id = R.string.phone_screen_button_text),
                        style = MaterialTheme.typography.h3
                    )
                }
            }
        }
    }


}

@Composable
fun PhoneScreenTypeButtons(
    stateClicked:(Int)->Unit
) {
    var stateOfButton1 by remember {
        mutableStateOf(false)
    }
    var stateOfButton2 by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomRadioButton(
            modifier = Modifier.size(70.dp),
            buttonImage = R.drawable.ic_tenant,
            state = stateOfButton1,
            text = "Tenant"
        ) {
            stateOfButton1 = true
            stateOfButton2 = false
            stateClicked(1)
        }

        CustomRadioButton(
            modifier = Modifier
                .size(70.dp),
            buttonImage = R.drawable.ic_landlord,
            state = stateOfButton2,
            text = "Landlord"
        ) {
            stateOfButton2 = true
            stateOfButton1 = false
            stateClicked(2)
        }
    }
}

@Composable
fun InputWrapper(
    modifier: Modifier,
    textModifier: Modifier,
    textStyle: TextStyle,
    textTitle: Int,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(BackgroundColorVariant),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = textModifier.fillMaxWidth(),
            text = stringResource(id = textTitle),
            style = textStyle,
        )
        content()
    }
}

@Composable
fun NumberInput(
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable() (() -> Unit)?,
    trailingIcon: @Composable () -> Unit,
    label: String = "",
    password: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(text)
        },
        placeholder = {
            if (label.isNotEmpty()) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.h3
                )
            }
        },
        textStyle = MaterialTheme.typography.h3,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(BackgroundColor),
        leadingIcon = leadingIcon,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        visualTransformation = if (password) PasswordVisualTransformation()
        else VisualTransformation.None
    )
}

@Composable
fun WrongInputMessage(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_xmark_solid),
            contentDescription = "",
            tint = Color.Red
        )
    }
}

@Composable
@Preview
fun WrongInputMessagePreview() {
    WrongInputMessage {

    }
}


@Composable
@Preview
fun PhoneScreenNumberInputPreview() {
    HomeRentPreview {
        InputWrapper(
            textTitle = R.string.phone_screen_phone_input_title,
            textStyle = MaterialTheme.typography.h2,
            textModifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp),
            modifier = Modifier.size(width = 400.dp, height = 150.dp)
        ) {
            NumberInput(
                onValueChange = {

                },
                label = "Phone number",
                modifier = Modifier.padding(top = 14.dp, start = 15.dp, end = 15.dp),
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_right_alt),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                        }
                    )
                },
                leadingIcon = {
                    Image(
                        ImageBitmap.imageResource(id = R.drawable.germany),
                        modifier = Modifier.size(40.dp),
                        contentDescription = ""
                    )
                }
            )
        }
    }
}


@Composable
@Preview
fun PhoneScreenPreview() {
    HomeRentPreview {
        PhoneScreen(navController = rememberNavController())
    }
}