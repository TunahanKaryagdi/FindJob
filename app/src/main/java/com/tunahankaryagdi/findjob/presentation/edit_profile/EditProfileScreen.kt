package com.tunahankaryagdi.findjob.presentation.edit_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomDropdownMenu
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedButton
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.edit_profile.components.AddSkillDialog
import com.tunahankaryagdi.findjob.presentation.profile.ProfileEvent
import com.tunahankaryagdi.findjob.presentation.profile.ProfileScreenContent
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.skills


@Composable
fun EditProfileScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel = hiltViewModel()
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    EditProfileScreen(
        modifier = modifier,
        uiState = uiState,
        onTrigger = viewModel::handleEvents
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    uiState: EditProfileUiState,
    onTrigger: (EditProfileEvent)->Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppbar(
                title =  {Text(text = stringResource(id = R.string.edit_profile))},
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.arraw_back)
                    )
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        EditProfileScreenContent(
            modifier = modifier.padding(it),
            uiState = uiState,
            onTrigger = onTrigger
        )
    }


}

@Composable
fun EditProfileScreenContent(
    modifier: Modifier = Modifier,
    uiState: EditProfileUiState,
    onTrigger: (EditProfileEvent)->Unit
) {

    if (uiState.isOpenDialog){
        AddSkillDialog(
            onTrigger = onTrigger,
            isExpandedDropdown = uiState.isExpandedDropdown,
            selectedDropdownValue = uiState.selectedDropdownValue,
            experienceValue = uiState.experienceValue
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(CustomTheme.spaces.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {


                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = stringResource(id = R.string.profile),
                    modifier = Modifier.clip(CircleShape)
                )

                SpacerHeight(size = CustomTheme.spaces.medium)

                Text(
                    text = stringResource(id = R.string.name),
                    style = CustomTheme.typography.bodyLarge
                )
                CustomOutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = uiState.name,
                    onValueChange = {}
                )

                SpacerHeight(size = CustomTheme.spaces.medium)

                Text(
                    text = stringResource(id = R.string.email),
                    style = CustomTheme.typography.bodyLarge
                )
                CustomOutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = uiState.email,
                    onValueChange = {}
                )

                SpacerHeight(size = CustomTheme.spaces.medium)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.skills),
                        style = CustomTheme.typography.bodyLarge,
                    )

                    Icon(
                        modifier = Modifier
                            .clickable {
                                onTrigger(EditProfileEvent.OnClickEdit)
                            },
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(id = R.string.edit_profile)
                    )
                }
            }
        }
        items(uiState.skills.size){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "• ${uiState.skills[it].experience} year experience in ${uiState.skills[it].name}",
                style = CustomTheme.typography.labelLarge
            )
        }
    }
}


