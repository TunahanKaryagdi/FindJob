package com.tunahankaryagdi.findjob.presentation.jobs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
        onTrigger = viewModel::handleEvents,
        navigateToApplication = navigateToApplication,
        navigatePop = navigatePop
    )

}

@Composable
fun JobScreen(
    modifier: Modifier = Modifier,
    uiState: JobsUiState,
    onTrigger: (JobsEvent) -> Unit,
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
                        color = CustomTheme.colors.secondaryText
                    )
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
            JobScreenContent(
                modifier = Modifier.padding(it),
                uiState = uiState,
                onTrigger = onTrigger,
                navigateToApplication = navigateToApplication
            )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun JobScreenContent(
    modifier: Modifier = Modifier,
    uiState: JobsUiState,
    onTrigger: (JobsEvent) -> Unit,
    navigateToApplication: (String) -> Unit
) {

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = { onTrigger(JobsEvent.OnRefresh)}
    )

    if (uiState.jobs.isEmpty()){
        CustomContentMessage(message = stringResource(id = R.string.no_job_yet))
    }

    Box() {
        LazyColumn(
            modifier = modifier
                .padding(CustomTheme.spaces.medium)
                .pullRefresh(pullRefreshState)
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
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = uiState.isLoading,
            state = pullRefreshState,
        )
    }
}