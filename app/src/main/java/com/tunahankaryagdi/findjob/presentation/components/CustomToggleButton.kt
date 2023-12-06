package com.tunahankaryagdi.findjob.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.JobTypes


@Composable
fun CustomToggleButton(
    modifier : Modifier = Modifier,
    text :JobTypes,
    isSelected : Boolean,
    onCheckedChange : (Boolean,JobTypes)-> Unit
) {


    Button(
        modifier = modifier,
        onClick = { onCheckedChange(!isSelected,text)},
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.secondaryBackground,
            contentColor = if (isSelected) CustomTheme.colors.tertiaryText else CustomTheme.colors.primaryText
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text.name,
            color = if (isSelected) CustomTheme.colors.tertiaryText else CustomTheme.colors.primaryText
        )
    }

}