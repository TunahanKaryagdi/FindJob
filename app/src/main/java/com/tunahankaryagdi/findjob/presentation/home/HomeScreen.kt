package com.tunahankaryagdi.findjob.presentation.home

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomOutlinedTextField
import com.tunahankaryagdi.findjob.presentation.components.CustomTinyButton
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.presentation.components.SpacerWidth
import com.tunahankaryagdi.findjob.presentation.home.components.AppDrawer
import com.tunahankaryagdi.findjob.presentation.home.components.PopularJobCard
import com.tunahankaryagdi.findjob.presentation.home.components.RecentPostCard
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import kotlinx.coroutines.launch


@Composable
fun HomeScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit,
    navigateToProfile: () -> Unit
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)
    LaunchedEffect(key1 = effect){
        if (effect == HomeEffect.NavigateToProfile){
            navigateToProfile()
        }
    }

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        navigateToDetail = navigateToDetail,
        onTrigger = viewModel::handleEvents
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail : (String) -> Unit,
    onTrigger: (HomeEvent) -> Unit,
    uiState: HomeUiState
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    AppDrawer(
        drawerState = drawerState,
        onClickDrawerItem = {onTrigger(HomeEvent.OnClickDrawerItem(it))}
    ) {
        Scaffold(
            modifier = modifier,
            containerColor = CustomTheme.colors.primaryBackground
        ){
            HomeScreenContent(
                modifier = modifier.padding(it),
                navigateToDetail = navigateToDetail,
                onClickOpenDrawer = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                uiState = uiState,
                onTrigger = onTrigger
            )
        }
    }

}


@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navigateToDetail : (String) -> Unit,
    onClickOpenDrawer: ()->Unit,
    onTrigger: (HomeEvent) -> Unit,
    uiState: HomeUiState,
) {



    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(CustomTheme.spaces.medium)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                CustomTinyButton(
                    icon = Icons.Filled.List,
                    onClick = {
                        onClickOpenDrawer()
                    }
                )
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = stringResource(id = R.string.job_image)
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

                    uiState.jobs[it].apply {
                        PopularJobCard(
                            modifier = Modifier
                                .clickable {
                                    navigateToDetail(this.id)
                                },
                            companyName = this.company.name,
                            jobName = this.title,
                            salary = this.salary,
                            location = this.location
                        )
                    }

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

            uiState.jobs[it].apply {
                RecentPostCard(
                    modifier = Modifier
                        .clickable {
                            navigateToDetail(this.id)
                        },
                    jobName = this.title,
                    jobType = this.type,
                    salary = this.salary
                )
                SpacerHeight(size = CustomTheme.spaces.small)
            }
        }
    }

}