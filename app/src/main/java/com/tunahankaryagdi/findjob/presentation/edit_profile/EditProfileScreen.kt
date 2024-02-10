package com.tunahankaryagdi.findjob.presentation.edit_profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomAsyncImage
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedButton
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.edit_profile.components.AddSkillDialog
import com.tunahankaryagdi.findjob.presentation.profile.components.CompanyStaffCard
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.ImageType


@Composable
fun EditProfileScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel = hiltViewModel(),
    navigatePop: () -> Unit,
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    EditProfileScreen(
        modifier = modifier,
        onTrigger = viewModel::handleEvents,
        uiState = uiState,
        navigatePop = navigatePop
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    navigatePop: () -> Unit,
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
                        modifier = Modifier
                            .clickable {
                                navigatePop()
                            },
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

    val context = LocalContext.current
    val width = LocalConfiguration.current.screenWidthDp
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult ={uri ->
            onTrigger(EditProfileEvent.OnChangeUri(uri, context))
        }
    )


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
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(CustomTheme.spaces.small)
    ) {

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                CustomAsyncImage(
                    modifier = Modifier
                        .size((width * 0.25).dp)
                        .clip(CircleShape),
                    model = "${Constants.BASE_IMAGE_URL}${uiState.image}",
                    type = ImageType.User
                )

                CustomOutlinedButton(
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    text = stringResource(id = R.string.upload_photo))
            }
        }

        item {
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
        }

        item{
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
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.experience),
                    style = CustomTheme.typography.bodyLarge
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
            if(uiState.companies.isEmpty()){
                Text(text = stringResource(id = R.string.experience))
            }
        }

        items(uiState.companies.size){
            CompanyStaffCard(companyStaff = uiState.companies[it])
        }

        item{
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

        items(uiState.skills.size){
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.experience_of, uiState.skills[it].name,uiState.skills[it].experience),
                style = CustomTheme.typography.labelLarge
            )
        }
    }
}


