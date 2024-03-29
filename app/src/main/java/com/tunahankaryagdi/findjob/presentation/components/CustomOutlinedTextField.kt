package com.tunahankaryagdi.findjob.presentation.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    size : Dp = 50.dp,
    singleLine : Boolean = true,
    placeholder: String = ""
) {


    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = CustomTheme.colors.secondaryBackground,
            focusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = CustomTheme.colors.primary
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        placeholder = {
            Text(text = placeholder)
        },
        readOnly = readOnly,
        singleLine = singleLine,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon

    )

}