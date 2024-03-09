package com.tunahankaryagdi.findjob.presentation.application

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.application.components.ApplicationCard
import com.tunahankaryagdi.findjob.presentation.application.components.UserDetailDialog
import com.tunahankaryagdi.findjob.presentation.components.CustomContentMessage
import com.tunahankaryagdi.findjob.presentation.components.CustomDropdownMenu
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.applicationStatuses


@Composable
fun ApplicationScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: ApplicationViewModel = hiltViewModel(),
    navigatePop: () -> Unit,
){

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    ApplicationScreen(
        modifier = modifier,
        onTrigger = viewModel::handleEvents,
        uiState = uiState,
        navigatePop = navigatePop
    )
}

@Composable
fun ApplicationScreen(
    modifier: Modifier = Modifier,
    onTrigger: (ApplicationEvent) -> Unit,
    navigatePop: () -> Unit,
    uiState: ApplicationUiState
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
                        text = if (uiState.applications.isEmpty()) stringResource(id = R.string.applications) else uiState.applications[0].job.title,
                        style = CustomTheme.typography.titleNormal
                    )
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        ApplicationScreenContent(
            modifier = modifier.padding(it),
            onTrigger = onTrigger,
            uiState = uiState
        )
    }

}

@Composable
fun ApplicationScreenContent(
    modifier: Modifier = Modifier,
    onTrigger: (ApplicationEvent) -> Unit,
    uiState: ApplicationUiState
) {

    val width = LocalConfiguration.current.screenWidthDp

    if (uiState.isOpenDialog && uiState.selectedUser != null && uiState.selectedApplication != null){
        UserDetailDialog(
            onTrigger = onTrigger,
            userDetail = uiState.selectedUser,
            message = uiState.selectedApplication.message
        )
    }

    LazyColumn(
        modifier = modifier
            .padding(CustomTheme.spaces.medium)
    ){

        item {
            CustomDropdownMenu(
                modifier = Modifier
                    .width((width * 0.4).dp),
                selectedDropdownValue = uiState.selectedFilter.name,
                items = applicationStatuses,
                onExpandedChange = {onTrigger(ApplicationEvent.OnDropdownExpandedChange(it))},
                onDismiss = {onTrigger(ApplicationEvent.OnDismissDropdown)},
                onClickItem = {onTrigger(ApplicationEvent.OnClickDropdownItem(it))},
                isExpanded = uiState.isExpandedDropdown
            )
        }

        if (uiState.filteredApplications.isEmpty()){
            item {
                CustomContentMessage(message = stringResource(id = R.string.no_applications_yet))
            }
        }
        else{
            items(uiState.filteredApplications.size){
                ApplicationCard(
                    modifier = Modifier
                        .clickable {
                            onTrigger(ApplicationEvent.OnClickUser(uiState.filteredApplications[it]))
                        },
                    application = uiState.filteredApplications[it]
                )
                SpacerHeight(size = CustomTheme.spaces.small)
            }
        }

    }


}
