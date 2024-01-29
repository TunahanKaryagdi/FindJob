package com.tunahankaryagdi.findjob.presentation.profile.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.profile.ProfileEvent
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBottomSheet(
    modifier: Modifier = Modifier,
    onTrigger: (ProfileEvent) -> Unit
) {


    ModalBottomSheet(
        onDismissRequest = {onTrigger(ProfileEvent.OnBottomSheetValueChange(false))},
        content = {
            TextButton(
                onClick = {
                    onTrigger(ProfileEvent.OnClickEditProfile)
                },
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = stringResource(id = R.string.edit_profile))
            }
            TextButton(
                onClick = {
                    onTrigger(ProfileEvent.OnClickLogout)
                },
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.log_out),
                    color = CustomTheme.colors.secondary
                )
            }
            SpacerHeight(size = CustomTheme.spaces.large)

        }
    )

}