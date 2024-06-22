package com.tunahankaryagdi.findjob.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomAsyncImage
import com.tunahankaryagdi.findjob.presentation.components.CustomCircularProgress
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomTinyButton
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.presentation.home.components.RecentPostCard
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.ImageType


@Composable
fun HomeScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
    navigateToRecommended : () -> Unit,
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)


    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToDetail = navigateToDetail,
        navigateToRecommended = navigateToRecommended,
        onTrigger = viewModel::handleEvents
    )

}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail : (String) -> Unit,
    navigateToRecommended : () -> Unit,
    onTrigger: (HomeEvent) -> Unit,
    uiState: HomeUiState
) {

    Scaffold(
        modifier = modifier,
        containerColor = CustomTheme.colors.primaryBackground
    ){
        HomeScreenContent(
            modifier = modifier.padding(it),
            navigateToDetail = navigateToDetail,
            navigateToRecommended = navigateToRecommended,
            uiState = uiState,
            onTrigger = onTrigger
        )
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navigateToDetail : (String) -> Unit,
    navigateToRecommended : () -> Unit,
    onTrigger: (HomeEvent) -> Unit,
    uiState: HomeUiState,
) {

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = { onTrigger(HomeEvent.OnRefresh)}
    )

    if (uiState.isLoading){
        CustomCircularProgress()
    }


    Box() {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(CustomTheme.spaces.medium)
                .pullRefresh(pullRefreshState)
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    CustomAsyncImage(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        model = "${Constants.BASE_IMAGE_URL}/${uiState.userImage}",
                        type = ImageType.User
                    )

                }

                SpacerHeight(size = CustomTheme.spaces.medium)

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomOutlinedTextField(
                        modifier = Modifier
                            .weight(1f),
                        value = uiState.searchText,
                        placeholder =  stringResource(id = R.string.search_here),
                        onValueChange = {onTrigger(HomeEvent.OnSearchValueChange(it))}
                    )

                    SpacerWidth(size = CustomTheme.spaces.small)

                    CustomTinyButton(
                        icon = Icons.Filled.Search,
                        onClick = {},
                    )
                }

                SpacerHeight(size = CustomTheme.spaces.medium)


                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = stringResource(id = R.string.recommended_for_you),
                        style = CustomTheme.typography.titleNormal
                    )
                    Text(
                        modifier = Modifier
                            .clickable {
                                navigateToRecommended()
                            },
                        text = stringResource(id = R.string.show_all),
                        style = CustomTheme.typography.bodySmall
                    )
                }


                SpacerHeight(size = CustomTheme.spaces.medium)

                Text(
                    text = stringResource(id = R.string.recent_post),
                    style = CustomTheme.typography.titleNormal
                )

                SpacerHeight(size = CustomTheme.spaces.small)
            }

            items(uiState.filteredJobs.size){

                if ((uiState.page) *20 -1 == it){
                    onTrigger(HomeEvent.OnGetNextPage(uiState.page))
                }
                RecentPostCard(
                    modifier = Modifier
                        .clickable {
                            navigateToDetail(uiState.filteredJobs[it].id)
                        },
                    job = uiState.filteredJobs[it]
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