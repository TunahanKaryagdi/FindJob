package com.tunahankaryagdi.findjob.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun CustomToggleButton(
    modifier : Modifier = Modifier,
    text :String,
    isSelected : Boolean,
    onCheckedChange : (Boolean)-> Unit
) {


    Button(
        modifier = modifier,
        onClick = { onCheckedChange(!isSelected)},
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.secondaryBackground,
            contentColor = if (isSelected) CustomTheme.colors.tertiaryText else CustomTheme.colors.primaryText
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) CustomTheme.colors.tertiaryText else CustomTheme.colors.primaryText
        )
    }

}