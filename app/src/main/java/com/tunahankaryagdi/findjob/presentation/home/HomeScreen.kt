package com.tunahankaryagdi.findjob.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.tunahankaryagdi.findjob.presentation.home.components.PopularJobCard
import com.tunahankaryagdi.findjob.presentation.home.components.RecentPostCard
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.ImageType


@Composable
fun HomeScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(key1 = true){
        viewModel.getJobs()
    }
    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToDetail = navigateToDetail,
        onTrigger = viewModel::handleEvents
    )

}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail : (String) -> Unit,
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
            uiState = uiState,
            onTrigger = onTrigger
        )
    }

}


@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navigateToDetail : (String) -> Unit,
    onTrigger: (HomeEvent) -> Unit,
    uiState: HomeUiState,
) {

    if (uiState.isLoading){
        CustomCircularProgress()
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(CustomTheme.spaces.medium)
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
                    value = "",
                    placeholder =  stringResource(id = R.string.search_here),
                    onValueChange = {

                    }
                )

                SpacerWidth(size = CustomTheme.spaces.small)

                CustomTinyButton(
                    icon = Icons.Filled.Search,
                    onClick = {},
                )
            }

            SpacerHeight(size = CustomTheme.spaces.medium)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.popular_job),
                    style = CustomTheme.typography.titleNormal
                )
                Text(
                    text = stringResource(id = R.string.show_all),
                    style = CustomTheme.typography.bodySmall
                )
            }

            SpacerHeight(size = CustomTheme.spaces.small)



            LazyRow() {
                items(uiState.jobs.size) {
                    PopularJobCard(
                        modifier = Modifier
                            .clickable {
                                navigateToDetail(uiState.jobs[it].id)
                            },
                        job = uiState.jobs[it]
                    )
                    SpacerWidth(size = CustomTheme.spaces.small)
                }

            }

            SpacerHeight(size = CustomTheme.spaces.medium)

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.recent_post),
                    style = CustomTheme.typography.titleNormal
                )
                Text(
                    text = stringResource(id = R.string.show_all),
                    style = CustomTheme.typography.bodySmall
                )
            }

            SpacerHeight(size = CustomTheme.spaces.small)
        }


        items(uiState.jobs.size){

                RecentPostCard(
                    modifier = Modifier
                        .clickable {
                            navigateToDetail(uiState.jobs[it].id)
                        },
                    job = uiState.jobs[it]
                )
                SpacerHeight(size = CustomTheme.spaces.small)

        }
    }

}