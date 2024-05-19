package com.tunahankaryagdi.findjob.presentation.recommended

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.recommended.components.RecommendedJobCard
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.TabItem


@Composable
fun RecommendedScreenRoute(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    navigatePop: () -> Unit,
    viewModel: RecommendedViewModel = hiltViewModel()
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    RecommendedScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToDetail = navigateToDetail,
        navigatePop = navigatePop,
        onTrigger = viewModel::handleEvents
    )
}

@Composable
fun RecommendedScreen(
    modifier: Modifier = Modifier,
    uiState: RecommendedUiState,
    navigateToDetail: (String) -> Unit,
    navigatePop: () -> Unit,
    onTrigger: (RecommendedEvent) -> Unit
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
                        text = stringResource(id = R.string.recommended_for_you),
                        color = CustomTheme.colors.secondaryText
                    )
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        RecommendedScreenContent(
            modifier = modifier.padding(it),
            uiState = uiState,
            navigateToDetail = navigateToDetail,
            onTrigger = onTrigger
        )
    }



}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecommendedScreenContent(
    modifier: Modifier = Modifier,
    uiState: RecommendedUiState,
    navigateToDetail: (String) -> Unit,
    onTrigger: (RecommendedEvent) -> Unit
) {

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = { onTrigger(RecommendedEvent.OnRefresh)}
    )

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        TabRow(
            selectedTabIndex = uiState.tabIndex,
            containerColor = CustomTheme.colors.primaryBackground,
            indicator = {}
        ) {

            TabItem.values().forEachIndexed { index, tabItem ->
                Tab(
                    selected = uiState.tabIndex == index,
                    onClick = {onTrigger(RecommendedEvent.OnClickTab(index))},
                    selectedContentColor = CustomTheme.colors.secondaryText,
                    unselectedContentColor = Color.Gray
                ) {
                    Text(
                        modifier = Modifier
                            .padding(15.dp),
                        text = tabItem.tabName,
                        style = CustomTheme.typography.body.copy(
                            color = if(uiState.tabIndex == index) Color.Black else Color.Gray,
                            fontWeight = if(uiState.tabIndex == index) FontWeight.Bold else FontWeight.Normal)
                    )
                }
            }
        }
        Box(modifier = Modifier){

            when(uiState.tabIndex){
                0 ->{
                    ProfileRecommendationList(
                        modifier = Modifier
                            .pullRefresh(pullRefreshState),
                        navigateToDetail = navigateToDetail,
                        jobs = uiState.profileRecommend
                    )
                }
                1 ->{
                    AppliedJobRecommendationList(
                        modifier = Modifier
                            .pullRefresh(pullRefreshState),
                        navigateToDetail = navigateToDetail,
                        jobs = uiState.appliedRecommend
                    )
                }
            }

            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = uiState.isLoading,
                state = pullRefreshState,
            )
        }




    }
}

@Composable
fun ProfileRecommendationList(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    jobs: List<Job>
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ){
        items(jobs.size){
            RecommendedJobCard(
                modifier = Modifier
                    .clickable {
                         navigateToDetail(jobs[it].id)
                    },
                job = jobs[it]
            )
        }
    }
}

@Composable
fun AppliedJobRecommendationList(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    jobs: List<Job>
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ){
        items(jobs.size){
            RecommendedJobCard(
                modifier = Modifier
                    .clickable {
                        navigateToDetail(jobs[it].id)
                    },
                job = jobs[it]
            )
        }
    }
}

