package com.tunahankaryagdi.findjob.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun CustomOutlinedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {

    Button(
        modifier = modifier
            .background(CustomTheme.colors.secondaryBackground, CircleShape)
            .border(1.dp,CustomTheme.colors.primary, CircleShape),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = CustomTheme.colors.primary,
            containerColor = CustomTheme.colors.secondaryBackground
        ),
    ) {
        Text(
            text = text,
            color = CustomTheme.colors.primary
        )
    }
}