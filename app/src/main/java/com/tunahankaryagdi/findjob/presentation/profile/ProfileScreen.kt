package com.tunahankaryagdi.findjob.presentation.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomAsyncImage
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.profile.components.CompanyStaffCard
import com.tunahankaryagdi.findjob.presentation.profile.components.ProfileBottomSheet
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.Constants
import com.tunahankaryagdi.findjob.utils.ImageType


@Composable
fun ProfileScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToEditProfile: () -> Unit,
    navigateToLogin: () -> Unit,
    navigatePop: () -> Unit,
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(key1 = effect){
        if (effect == ProfileEffect.NavigateToEditProfile){
            navigateToEditProfile()
        }
        if (effect == ProfileEffect.NavigateToLogin){
            navigateToLogin()
        }
    }


    ProfileScreen(
        modifier = modifier,
        uiState = uiState,
        onTrigger = viewModel::handleEvents,
        navigatePop = navigatePop
    )
}


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    onTrigger: (ProfileEvent) -> Unit,
    navigatePop: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppbar(
                title =  {Text(text = stringResource(id = R.string.profile))},
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
                actions = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onTrigger(ProfileEvent.OnBottomSheetValueChange(true))
                            },
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = stringResource(id = R.string.more))
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        ProfileScreenContent(
            modifier = modifier.padding(it),
            uiState = uiState,
            onTrigger = onTrigger
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    onTrigger: (ProfileEvent) -> Unit
){

    val width = LocalConfiguration.current.screenWidthDp
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = { onTrigger(ProfileEvent.OnRefresh)}
    )

    if (uiState.isBottomSheetVisible){
        ProfileBottomSheet(
            onTrigger = onTrigger
        )
    }



    uiState.userDetail?.let {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(CustomTheme.spaces.medium)
                .pullRefresh(pullRefreshState),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(CustomTheme.spaces.small)
        ) {

            item {
                CustomAsyncImage(
                    modifier = Modifier
                        .size((width * 0.25).dp)
                        .clip(CircleShape),
                    model = "${Constants.BASE_IMAGE_URL}${uiState.userDetail.image}",
                    type = ImageType.User
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.name),
                    style = CustomTheme.typography.bodyLarge
                )
                Text(
                    text = uiState.userDetail.nameSurname,
                    style = CustomTheme.typography.body
                )
            }

            item {
                Text(
                    text = stringResource(id = R.string.email),
                    style = CustomTheme.typography.bodyLarge
                )
                Text(
                    text = uiState.userDetail.email,
                    style = CustomTheme.typography.body
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.experience),
                    style = CustomTheme.typography.bodyLarge
                )
                if(uiState.companies.isEmpty()){
                    Text(text = stringResource(id = R.string.experience))
                }
            }

            items(uiState.companies.size){
                CompanyStaffCard(companyStaff = uiState.companies[it])
            }

            item {
                Text(
                    text = stringResource(id = R.string.skills),
                    style = CustomTheme.typography.bodyLarge
                )
                if(uiState.userDetail.skills.isEmpty()){
                    Text(text = stringResource(id = R.string.no_skills))
                }
            }
            items(uiState.userDetail.skills.size){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.experience_of, uiState.userDetail.skills[it].name,uiState.userDetail.skills[it].experience),
                    style = CustomTheme.typography.labelLarge
                )

            }

        }
        PullRefreshIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            refreshing = uiState.isLoading,
            state = pullRefreshState,
        )
    }
}

