package com.miracle.validatekotlin.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.miracle.validatekotlin.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val textHeading = FontFamily(
    Font(R.font.heading, FontWeight.Bold),
)

val textBold = FontFamily(
    Font(R.font.bold, FontWeight.Bold),
)

val textReg = FontFamily(
    Font(R.font.reg, FontWeight.Normal),
)