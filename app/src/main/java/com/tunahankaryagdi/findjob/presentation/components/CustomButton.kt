package com.tunahankaryagdi.findjob.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    height : Dp = 50.dp,
    onClick: () -> Unit,
    text: String
) {

    Button(
        modifier = modifier
            .height(height),
        shape = RoundedCornerShape(CustomTheme.spaces.small),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = CustomTheme.colors.primary,
            contentColor = CustomTheme.colors.tertiaryText
        )
    ) {
        Text(
            text = text,
            style = CustomTheme.typography.body.copy(CustomTheme.colors.tertiaryText)
        )
    }
}