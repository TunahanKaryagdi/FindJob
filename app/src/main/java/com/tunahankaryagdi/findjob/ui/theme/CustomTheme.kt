package com.tunahankaryagdi.findjob.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CustomSpaces(
    val extraSmall : Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 48.dp,
)



class CustomColors(
    primary: Color,
    secondary: Color,
    primaryText: Color,
    secondaryText: Color,
    tertiaryText: Color,
    primaryBackground: Color,
    secondaryBackground : Color,
) {
    var primary by mutableStateOf(primary)
        private set

    var secondary by mutableStateOf(secondary)
        private set

    var primaryText by mutableStateOf(primaryText)
        private set

    var secondaryText by mutableStateOf(secondaryText)
        private set

    var tertiaryText by mutableStateOf(tertiaryText)
        private set

    var primaryBackground by mutableStateOf(primaryBackground)
        private set

    var secondaryBackground by mutableStateOf(secondaryBackground)
        private set


    fun copy(
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        primaryText: Color = this.primaryText,
        secondaryText: Color = this.secondaryText,
        primaryBackground: Color = this.primaryBackground,
        secondaryBackground: Color = this.secondaryBackground,

        ) = CustomColors(
        primary = primary,
        secondary = secondary,
        primaryText = primaryText,
        secondaryText = secondaryText,
        tertiaryText = tertiaryText,
        primaryBackground = primaryBackground,
        secondaryBackground = secondaryBackground
    )
    fun updateColorsFrom(other: CustomColors) {
        primary = other.primary
        secondary = other.secondary
        primaryText = other.primaryText
        secondaryText = secondaryText
        tertiaryText = other.tertiaryText
        primaryBackground = other.primaryBackground
        secondaryBackground = other.secondaryBackground
    }
}


object CustomTheme {

    val colors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: CustomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val spaces: CustomSpaces
        @Composable
        @ReadOnlyComposable
        get() = LocalSpaces.current

}
fun lightColors() = CustomColors(
    primary = primaryLight,
    secondary = secondaryLight,
    primaryText = primaryTextLight,
    secondaryText = secondaryTextLight,
    tertiaryText = tertiaryTextLight,
    primaryBackground = primaryBackgroundLight,
    secondaryBackground = secondaryBackgroundLight,
)

fun darkColors() = CustomColors(
    primary = primaryLight,
    secondary = secondaryLight,
    primaryText = primaryTextLight,
    secondaryText = secondaryTextLight,
    tertiaryText = tertiaryTextLight,
    primaryBackground = primaryBackgroundLight,
    secondaryBackground = secondaryBackgroundLight,
)

val LocalSpaces = staticCompositionLocalOf { CustomSpaces() }

val LocalColors = staticCompositionLocalOf { lightColors() }

val LocalTypography = staticCompositionLocalOf {
    CustomTypography()
}


@Composable
fun CustomTheme(
    spaces: CustomSpaces = CustomTheme.spaces,
    typography: CustomTypography = CustomTheme.typography,
    colors: CustomColors = CustomTheme.colors,
    darkColors: CustomColors? = null,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val currentColor = remember { if (darkColors != null && darkTheme) darkColors else colors }
    val rememberedColors = remember { currentColor.copy() }.apply { updateColorsFrom(currentColor) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalSpaces provides spaces,
        LocalTypography provides typography,
    ) {
        ProvideTextStyle(typography.body, content = content)
    }
}