package com.tunahankaryagdi.findjob.presentation.add.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.add.AddEvent
import com.tunahankaryagdi.findjob.presentation.components.CustomDropdownMenu
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.edit_profile.EditProfileEvent
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.skills


@Composable
fun AddQualificationDialog(
    onTrigger: (AddEvent) -> Unit,
    qualificationValue: String,
    experienceValue: String,
    isExpandedDropdown: Boolean
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
                tint = CustomTheme.colors.primary,
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
                tint = CustomTheme.colors.secondary,
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
            Column() {
                CustomDropdownMenu(
                    items = skills,
                    selectedDropdownValue = qualificationValue,
                    onDismiss = { onTrigger(AddEvent.OnDismissDropdown) },
                    onClickItem = {onTrigger(AddEvent.OnClickDropdownItem(it))},
                    onExpandedChange = {onTrigger(AddEvent.OnDropdownExpandedChange(it))},
                    isExpanded = isExpandedDropdown
                )
                CustomOutlinedTextField(
                    value = experienceValue,
                    placeholder = stringResource(id = R.string.experience),
                    onValueChange = {onTrigger(AddEvent.OnExperienceValueChange(it))},
                    keyboardType = KeyboardType.Number
                )

            }
        }
    )
}