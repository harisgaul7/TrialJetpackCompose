package com.mountainbb.trialjetpackcompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mountainbb.trialjetpackcompose.R

val MontserratFontFamily = FontFamily(
    listOf(
        Font(R.font.montserrat_regular),
        Font(R.font.montserrat_medium, FontWeight.Medium),
        Font(R.font.montserrat_semibold, FontWeight.SemiBold)
    )
)
val OpensansFontBold = FontFamily(
    listOf(
        Font(R.font.opensans_bold, FontWeight.Bold)
    )
)

val OpensansFontFamily = FontFamily(
    listOf(
        Font(R.font.opensans_regular),
        Font(R.font.opensans_bold, FontWeight.Bold),
        Font(R.font.opensans_bolditalic),
        Font(R.font.opensans_condbold),
        Font(R.font.opensans_condlight),
        Font(R.font.opensans_condlightitalic),
        Font(R.font.opensans_extrabold),
        Font(R.font.opensans_extrabolditalic),
        Font(R.font.opensans_light),
        Font(R.font.opensans_lightitalic),
        Font(R.font.opensans_italic),
        Font(R.font.opensans_semibold),
        Font(R.font.opensans_semibolditalic),
    )
)


val MontserratFontSemiBold = FontFamily(
    listOf(
        Font(R.font.montserrat_semibold)
    )
)

val SeptimusFontFamily = FontFamily(
    listOf(
        Font(R.font.septimus_woff),
        Font(R.font.septimus_woff, FontWeight.Medium),
        Font(R.font.septimus_woff, FontWeight.SemiBold)
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    // Title Medium - Montserrat 16/24 . 0.15px
    titleMedium = TextStyle(
        fontFamily = MontserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)