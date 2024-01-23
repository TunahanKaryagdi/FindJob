package com.tunahankaryagdi.findjob.presentation.jobs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomContentMessage
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.jobs.components.JobCard
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun JobScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: JobsViewModel = hiltViewModel(),
    navigateToApplication: (String) -> Unit,
    navigatePop: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    JobScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToApplication = navigateToApplication,
        navigatePop = navigatePop
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobScreen(
    modifier: Modifier = Modifier,
    uiState: JobsUiState,
    navigateToApplication: (String) -> Unit,
    navigatePop: () -> Unit,
){

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppbar(
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                              navigatePop()
                            },
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.arraw_back)
                    )
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.jobs),
                        style = CustomTheme.typography.titleNormal
                    )
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
            JobScreenContent(
                modifier = Modifier.padding(it),
                uiState = uiState,
                navigateToApplication = navigateToApplication
            )
    }
}

@Composable
fun JobScreenContent(
    modifier: Modifier = Modifier,
    uiState: JobsUiState,
    navigateToApplication: (String) -> Unit
) {

    if (uiState.jobs.isEmpty()){
        CustomContentMessage(message = stringResource(id = R.string.no_job_yet))
    }

    LazyColumn(
        modifier = modifier
            .padding(CustomTheme.spaces.medium)
    ){
        items(uiState.jobs.size){
            JobCard(
                modifier = Modifier
                    .clickable {
                        navigateToApplication(uiState.jobs[it].id)
                    },
                job = uiState.jobs[it]
            )
            SpacerHeight(size = CustomTheme.spaces.small)
        }
    }
}