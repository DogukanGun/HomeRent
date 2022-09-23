package com.dag.homerent.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val ButtonColor = Color(0xFFF3B007)
val HomeListRowColor = Color(0xFFF3F4F8)
val HomeListRowButtonColor = Color(0xFFFA6909)
val UnSelectedBottomItem = Color(0xFF929292)
val BackgroundColor = Color(0xFF917AB2)
val BackgroundColorVariant = Color(0xFF4D3370)
val MainActivityBackground = Color(0xFFF9F9F9)
val HomeFilterButtonBackground = Color(0xFF3546CB).copy(alpha = 0.08f)
val HomeFilterButtonImageColor = Color(0xFF735CF8)
val CustomButtonInnerColor = Color(0xFF6D558F)
val CustomButtonInnerSelectedColor = Color(0xFFD3D3D3).copy(alpha = 0.5f)

val Background = Brush.verticalGradient(
    colors = listOf(
        BackgroundColor,
        BackgroundColorVariant
    ),
    startY = 400f
)