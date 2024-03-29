package com.tunahankaryagdi.findjob.presentation.my_applications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.tunahankaryagdi.findjob.presentation.my_applications.components.ApplicationCard
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.home.HomeEvent
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun MyApplicationsScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: ApplicationsViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
    navigatePop: () -> Unit,
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    MyApplicationsScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToDetail = navigateToDetail,
        navigatePop = navigatePop,
        onTrigger = viewModel::handleEvents
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApplicationsScreen(
    modifier: Modifier = Modifier,
    uiState: MyApplicationsUiState,
    navigateToDetail: (String) -> Unit,
    navigatePop: () -> Unit,
    onTrigger: (MyApplicationsEvent) -> Unit
) {


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
                        text = stringResource(id = R.string.applications),
                        style = CustomTheme.typography.titleNormal
                    )
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        MyApplicationsScreenContent(
            modifier = modifier.padding(it),
            uiState = uiState,
            navigateToDetail = navigateToDetail,
            onTrigger = onTrigger
        )
    }


}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyApplicationsScreenContent(
    modifier: Modifier = Modifier,
    uiState: MyApplicationsUiState,
    navigateToDetail: (String) -> Unit,
    onTrigger: (MyApplicationsEvent) -> Unit
) {

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = { onTrigger(MyApplicationsEvent.OnRefresh)}
    )

    if (uiState.applications.isEmpty()){
        CustomContentMessage(message = stringResource(id = R.string.no_applications_yet))
    }

    Box() {
        LazyColumn(
            modifier = modifier
                .padding(CustomTheme.spaces.medium)
                .pullRefresh(pullRefreshState)
        ){
            items(uiState.applications.size){
                ApplicationCard(
                    modifier = Modifier
                        .clickable {
                            navigateToDetail(uiState.applications[it].job.id)
                        },
                    application = uiState.applications[it]
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