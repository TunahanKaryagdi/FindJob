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
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.tunahankaryagdi.findjob.utils.JobTypes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AddScreenRoute(
    modifier: Modifier = Modifier,
    viewModel : AddViewModel = hiltViewModel()
) {

    val uiState by  viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)


    AddScreen(
        modifier = modifier,
        uiState = uiState,
        onTrigger = viewModel::handleEvents,
        effect = effect
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    modifier: Modifier = Modifier,
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
            qualification = uiState.qualification
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
                enumValues<JobTypes>().forEach {
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
                                imageVector = Icons.Outlined.Star,
                                contentDescription = stringResource(id = R.string.salary)
                            )
                        }
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
            Text(text = "• ${uiState.qualifications[it]}")
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