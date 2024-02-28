package com.tunahankaryagdi.findjob.presentation.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.add.components.AddQualificationDialog
import com.tunahankaryagdi.findjob.presentation.components.CustomButton
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomToggleButton
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.JobType


@Composable
fun AddScreenRoute(
    modifier: Modifier = Modifier,
    viewModel : AddViewModel = hiltViewModel(),
    navigateToAddCompany: () -> Unit,
) {

    val uiState by  viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)


    AddScreen(
        modifier = modifier,
        navigateToAddCompany = navigateToAddCompany,
        uiState = uiState,
        onTrigger = viewModel::handleEvents,
        effect = effect
    )
}


@Composable
fun AddScreen(
    modifier: Modifier = Modifier,
    navigateToAddCompany: () -> Unit,
    uiState: AddUiState,
    onTrigger: (AddEvent) -> Unit,
    effect: AddEffect?
){
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = effect){
        when (effect){
            is AddEffect.ShowMessage->{
                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = effect.message,
                    actionLabel = "Do something."
                )
            }
            else->{

            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost ={ scaffoldState.snackbarHostState},
        topBar = {
            CustomTopAppbar(
                title = {
                    Text(
                        text = stringResource(id = R.string.add_job),
                        style = CustomTheme.typography.titleNormal
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                navigateToAddCompany()
                            },
                        imageVector = Icons.Outlined.ArrowForward,
                        contentDescription = stringResource(id = R.string.add_company))
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        AddScreenContent(
            modifier = Modifier.padding(it),
            uiState = uiState,
            onTrigger = onTrigger
        )
    }

}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddScreenContent(
    modifier: Modifier = Modifier,
    uiState: AddUiState,
    onTrigger: (AddEvent)->Unit
) {

    if (uiState.isOpenDialog){
        AddQualificationDialog(
            onTrigger = onTrigger,
            qualificationValue = uiState.qualification,
            experienceValue = uiState.experienceValue,
            isExpandedDropdown = uiState.isExpandedDropdown
        )
    }

    LazyColumn(
        modifier = modifier
            .padding(CustomTheme.spaces.medium)
    ) {

        item {

            Text(
                text = stringResource(id = R.string.job_type),
                style = CustomTheme.typography.bodyLarge
            )

            FlowRow() {
                enumValues<JobType>().forEach {
                    CustomToggleButton(
                        text = it,
                        isSelected = uiState.selectedJobType == it,
                        onCheckedChange = {value->
                            onTrigger(AddEvent.OnClickJobType(value))
                        }
                    )
                    SpacerWidth(size = CustomTheme.spaces.small)
                }
            }

            SpacerHeight(size = CustomTheme.spaces.medium)
            Text(
                text = stringResource(id = R.string.title),
                style = CustomTheme.typography.bodyLarge
            )
            CustomOutlinedTextField(
                value = uiState.title,
                onValueChange = {onTrigger(AddEvent.OnTitleValueChange(it))}
            )

            SpacerHeight(size = CustomTheme.spaces.medium)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ){
                    Text(
                        text = stringResource(id = R.string.location),
                        style = CustomTheme.typography.bodyLarge
                    )
                    CustomOutlinedTextField(
                        value = uiState.location,
                        onValueChange = {onTrigger(AddEvent.OnLocationValueChange(it))},
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = stringResource(id = R.string.location)
                            )
                        }
                    )
                }

                SpacerWidth(size = CustomTheme.spaces.medium)

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.salary),
                        style = CustomTheme.typography.bodyLarge
                    )
                    CustomOutlinedTextField(
                        value = uiState.salary,
                        onValueChange = {onTrigger(AddEvent.OnSalaryValueChange(it))},
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_salary),
                                contentDescription = stringResource(id = R.string.salary)
                            )
                        },
                        keyboardType = KeyboardType.Number
                    )
                }
            }

            SpacerHeight(size = CustomTheme.spaces.medium)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.qualifications),
                    style = CustomTheme.typography.bodyLarge
                )
                Icon(
                    modifier = Modifier
                        .clickable {
                            onTrigger(AddEvent.OnClickEdit)
                        },
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = stringResource(id = R.string.add_qualification)
                )
            }

            SpacerHeight(size = CustomTheme.spaces.medium)
        }
        items(uiState.qualifications.size){
            Text(
                text = "• ${uiState.qualifications[it].experience} year experience in ${uiState.qualifications[it].name}",
                style = CustomTheme.typography.labelLarge
            )
            SpacerHeight(size = CustomTheme.spaces.small)
        }

        item {
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    onTrigger(AddEvent.OnClickPost)
                },
                text = stringResource(id = R.string.post) )
        }
    }
}