package com.tunahankaryagdi.findjob.presentation.edit_profile.components

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
import com.tunahankaryagdi.findjob.presentation.components.CustomDropdownMenu
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.edit_profile.EditProfileEvent
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.skills

@Composable
fun AddSkillDialog(
    onTrigger: (EditProfileEvent) -> Unit,
    selectedDropdownValue: String,
    isExpandedDropdown: Boolean,
    experienceValue: String,
) {

    AlertDialog(
        onDismissRequest = {
            onTrigger(EditProfileEvent.OnDismissDialog)
        },
        confirmButton = {
            Icon(
                modifier = Modifier
                    .clickable {
                        onTrigger(EditProfileEvent.OnConfirmDialog)
                    },
                imageVector = Icons.Default.Done,
                tint = CustomTheme.colors.primary,
                contentDescription = stringResource(id = R.string.done)
            )
        },
        dismissButton = {
            Icon(
                modifier = Modifier
                    .clickable {
                        onTrigger(EditProfileEvent.OnDismissDialog)
                    },
                imageVector = Icons.Default.Close,
                tint = CustomTheme.colors.secondary,
                contentDescription = stringResource(id = R.string.close)
            )
        },
        containerColor = CustomTheme.colors.primaryBackground,
        titleContentColor = CustomTheme.colors.primary,
        textContentColor = CustomTheme.colors.secondaryText,
        title = {
            Text(text = stringResource(id = R.string.skill))
        },
        text = {
            Column() {
                CustomDropdownMenu(
                    items = skills,
                    selectedDropdownValue = selectedDropdownValue,
                    onDismiss = { onTrigger(EditProfileEvent.OnDismissDropdown) },
                    onClickItem = {onTrigger(EditProfileEvent.OnClickDropdownItem(it))},
                    onExpandedChange = {onTrigger(EditProfileEvent.OnDropdownExpandedChange(it))},
                    isExpanded = isExpandedDropdown
                )
                CustomOutlinedTextField(
                    value = experienceValue,
                    placeholder = stringResource(id = R.string.experience),
                    onValueChange = {onTrigger(EditProfileEvent.OnExperienceValueChange(it))},
                    keyboardType = KeyboardType.Number
                )

            }
        }
    )
}