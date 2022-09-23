package com.dag.homerent.composebase.radiobutton

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dag.homerent.R
import com.dag.homerent.composebase.HomeRentPreview
import com.dag.homerent.ui.theme.Background
import com.dag.homerent.ui.theme.ButtonColor
import com.dag.homerent.ui.theme.CustomButtonInnerColor
import com.dag.homerent.ui.theme.CustomButtonInnerSelectedColor


@Composable
fun CustomRadioButton(
    modifier: Modifier,
    buttonImage: Int,
    state: Boolean,
    text: String = "Landord",
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            modifier = modifier
                .background(
                    if(!state)
                        Background
                    else
                        Brush.verticalGradient(
                            colors = listOf(
                                ButtonColor,
                                ButtonColor
                            )
                        )
                ),
            onClick = {
                onClick()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.Transparent
            ),
            contentPadding = PaddingValues(bottom = 0.dp, top = 8.dp, start = 8.dp, end = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                    )
                    .background(
                        if (!state)
                            Brush.verticalGradient(
                                colors = listOf(
                                    CustomButtonInnerColor,
                                    CustomButtonInnerColor
                                )
                            )
                        else
                            Brush.verticalGradient(
                                colors = listOf(
                                    CustomButtonInnerSelectedColor,
                                    CustomButtonInnerSelectedColor
                                )
                            )
                    )
                    .align(alignment = Alignment.Bottom),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(id = buttonImage),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(bottom = 3.dp, top = 5.dp, start = 5.dp, end = 5.dp)
                        .heightIn(max = 40.dp)
                )
            }
        }
        Text(
            text = text,
            style = MaterialTheme.typography.h3
        )
    }

}


@Composable
@Preview
fun CustomRadioButtonPreview() {
    var state by remember {
        mutableStateOf(false)
    }
    HomeRentPreview {
        CustomRadioButton(
            modifier = Modifier.size(50.dp),
            buttonImage = R.drawable.ic_tenant,
            state = state,
            onClick = {
                state = !state
            },
        )
    }
}