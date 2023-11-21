package com.tunahankaryagdi.findjob.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {

    Button(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = CustomTheme.colors.primary,
            contentColor = CustomTheme.colors.tertiaryText
        )
    ) {
        Text(text = text)
    }
}