package com.dag.homerent.composebase.dropbox

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dag.homerent.R
import com.dag.homerent.composebase.HomeRentPreview


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropBox(
    modifier: Modifier,
    listOfContent: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(listOfContent[0]) }

    ExposedDropdownMenuBox(
        modifier=modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        if (!expanded){
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = {  },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
        }else{
            LazyColumn{
                item {
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        listOfContent.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedOptionText = selectionOption
                                    expanded = false
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun CustomDropDownButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
        ),
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        border = BorderStroke(color = Color.Transparent, width = 0.dp)
    ) {
        content()
    }
}

@Composable
@Preview
fun CustomDropBoxPreview() {
    var textFieldValue by remember {
        mutableStateOf("")
    }
    HomeRentPreview {
        CustomDropBox(
            modifier=Modifier.padding(10.dp),
            listOfContent = listOf("GR", "USA", "TR"),
            onSelected = {
                textFieldValue = it
            }
        )
        Text(text = textFieldValue)
    }
}