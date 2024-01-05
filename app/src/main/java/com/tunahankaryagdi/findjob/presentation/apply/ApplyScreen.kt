package com.tunahankaryagdi.findjob.presentation.apply

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.add.AddEvent
import com.tunahankaryagdi.findjob.presentation.components.CustomButton
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun ApplyScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: ApplyViewModel = hiltViewModel()
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()


    ApplyScreen(
        modifier = modifier,
        uiState = uiState,
        onTrigger = viewModel::handleEvents
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyScreen(
    modifier: Modifier = Modifier,
    uiState: ApplyUiState,
    onTrigger: (ApplyEvent) -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppbar(
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.arraw_back)
                    )
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.job_apply),
                        style = CustomTheme.typography.titleNormal
                    )
                }
            )
                 },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        ApplyScreenContent(
            modifier = Modifier.padding(it),
            uiState = uiState,
            onTrigger = onTrigger
        )
    }


}

@Composable
fun ApplyScreenContent(
    modifier: Modifier = Modifier,
    uiState: ApplyUiState,
    onTrigger: (ApplyEvent) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(CustomTheme.spaces.medium)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.name).plus(" " + stringResource(id = R.string.surname))
                )
                CustomOutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = uiState.nameSurname,
                    onValueChange = { onTrigger(ApplyEvent.OnNameValueChange(it)) },
                    placeholder = stringResource(id = R.string.name).plus(" " + stringResource(id = R.string.surname))
                )

                SpacerHeight(size = CustomTheme.spaces.medium)

                Text(text = stringResource(id = R.string.email))
                CustomOutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = uiState.email,
                    onValueChange = { onTrigger(ApplyEvent.OnEmailValueChange(it)) },
                    placeholder = stringResource(id = R.string.email)
                )

                SpacerHeight(size = CustomTheme.spaces.medium)

                Text(text = stringResource(id = R.string.message))
                CustomOutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = uiState.message,
                    onValueChange = {onTrigger(ApplyEvent.OnMessageValueChange(it))},
                    size = 100.dp,
                    singleLine = false,
                    placeholder = stringResource(id = R.string.message)
                )

                SpacerHeight(size = CustomTheme.spaces.medium)
            }
            item {
                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    onClick = { onTrigger(ApplyEvent.OnClickApply) },
                    text = stringResource(id = R.string.apply_now)
                )
            }
            

        }

    }
}