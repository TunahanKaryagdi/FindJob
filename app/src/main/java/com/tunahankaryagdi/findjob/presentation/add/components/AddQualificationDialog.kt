package com.tunahankaryagdi.findjob.presentation.add.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.add.AddEvent
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun AddQualificationDialog(
    onTrigger: (AddEvent) -> Unit,
    qualification: String
) {

    AlertDialog(
        onDismissRequest = {
            onTrigger(AddEvent.OnDismissDialog)
        },
        confirmButton = {
            Icon(
                modifier = Modifier
                    .clickable {
                        onTrigger(AddEvent.OnConfirmDialog)
                    },
                imageVector = Icons.Default.Done ,
                contentDescription = stringResource(id = R.string.done)
            )
        },
        dismissButton = {
            Icon(
                modifier = Modifier
                    .clickable {
                        onTrigger(AddEvent.OnDismissDialog)
                    },
                imageVector = Icons.Default.Close ,
                contentDescription = stringResource(id = R.string.close)
            )
        },
        containerColor = CustomTheme.colors.primaryBackground,
        titleContentColor = CustomTheme.colors.primary,
        textContentColor = CustomTheme.colors.secondaryText,
        title = {
            Text(text = stringResource(id = R.string.qualification))
        },
        text = {
            CustomOutlinedTextField(value = qualification, onValueChange = { onTrigger(AddEvent.OnQualificationValueChange(it))} )
        }
    )
}