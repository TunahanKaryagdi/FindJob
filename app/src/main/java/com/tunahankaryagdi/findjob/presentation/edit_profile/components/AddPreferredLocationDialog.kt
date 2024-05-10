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
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomDropdownMenu
import com.tunahankaryagdi.findjob.presentation.edit_profile.EditProfileEvent
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.locations

@Composable
fun AddPreferredLocationDialog(
    onTrigger: (EditProfileEvent) -> Unit,
    selectedPreferredLocation: String,
    isExpandedDropdown: Boolean,
) {

    AlertDialog(
        onDismissRequest = {
            onTrigger(EditProfileEvent.OnDismissDialog)
        },
        confirmButton = {
            Icon(
                modifier = Modifier
                    .clickable {
                        onTrigger(EditProfileEvent.OnConfirmPreferredLocationDialog)
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
            Text(text = stringResource(id = R.string.location))
        },
        text = {
            Column() {
                CustomDropdownMenu(
                    items = locations,
                    selectedDropdownValue = selectedPreferredLocation,
                    onDismiss = { onTrigger(EditProfileEvent.OnDismissDropdown) },
                    onClickItem = {onTrigger(EditProfileEvent.OnClickPreferredLocationDropdownItem(it))},
                    onExpandedChange = {onTrigger(EditProfileEvent.OnDropdownExpandedChange(it))},
                    isExpanded = isExpandedDropdown,
                    placeholder = stringResource(id = R.string.select_a_location)
                )


            }
        }
    )
}