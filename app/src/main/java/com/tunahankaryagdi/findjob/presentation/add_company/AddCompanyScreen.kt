package com.tunahankaryagdi.findjob.presentation.add_company

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.tunahankaryagdi.findjob.presentation.components.CustomButton
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedButton
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.ImageType


@Composable
fun AddCompanyRoute(
    modifier: Modifier = Modifier,
    viewModel: AddCompanyViewModel = hiltViewModel(),
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()

    AddCompanyScreen(
        modifier = modifier,
        uiState = uiState,
        onTrigger = viewModel::handleEvents
    )
}



@Composable
fun AddCompanyScreen(
    modifier: Modifier = Modifier,
    uiState: AddCompanyUiState,
    onTrigger: (AddCompanyEvent) -> Unit
) {

    Scaffold(
        topBar = {
            CustomTopAppbar(
                title = {
                    Text(
                        text = stringResource(id = R.string.add_company),
                        style = CustomTheme.typography.titleNormal
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.arraw_back)
                    )
                }
            )
        }
    ) {
        AddCompanyScreenContent(
            modifier = modifier.padding(it),
            uiState = uiState,
            onTrigger = onTrigger
        )
    }

}


@Composable
fun AddCompanyScreenContent(
    modifier: Modifier = Modifier,
    uiState: AddCompanyUiState,
    onTrigger: (AddCompanyEvent) -> Unit
) {

    val width = LocalConfiguration.current.screenWidthDp
    val context = LocalContext.current
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult ={uri ->
            uri?.let { onTrigger(AddCompanyEvent.OnUriChange(it))}
        }
    )

    Column(
        modifier = modifier
            .padding(CustomTheme.spaces.medium),
        verticalArrangement = Arrangement.spacedBy(CustomTheme.spaces.medium)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CustomAsyncImage(
                modifier = Modifier
                    .size((width * 0.25).dp)
                    .clip(CircleShape),
                model = uiState.selectedImage,
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

        CustomOutlinedTextField(
            value = uiState.name,
            onValueChange = {onTrigger(AddCompanyEvent.OnNameValueChange(it))},
            placeholder = stringResource(id = R.string.name)
        )

        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.post),
            onClick = { onTrigger(AddCompanyEvent.OnClickPost(context))},
        )
    }
}