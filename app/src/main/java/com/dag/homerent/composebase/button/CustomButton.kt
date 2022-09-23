package com.dag.homerent.composebase.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dag.homerent.ui.theme.HomeRentTheme


@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    border: BorderStroke = BorderStroke(1.dp, Color.Black),
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
){
    Button(
        modifier = modifier,
        onClick = onClick,
        border = border,
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(7.dp)
    ) {
        content()
    }
}

@Composable
@Preview
fun CustomButtonPreview(){
    HomeRentTheme {
        CustomButton(
            onClick = {},
            color = MaterialTheme.colors.primary,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text(
                text = "Get Started",
                color = Color.White
            )
        }
    }
}