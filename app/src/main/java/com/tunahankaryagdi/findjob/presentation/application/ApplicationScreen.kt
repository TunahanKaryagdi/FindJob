package com.tunahankaryagdi.findjob.presentation.application

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
import com.tunahankaryagdi.findjob.presentation.application.components.ApplicationCard
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.presentation.components.SpacerHeight
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun ApplicationScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: ApplicationViewModel = hiltViewModel()
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    ApplicationScreen(
        modifier = modifier,
        uiState = uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationScreen(
    modifier: Modifier = Modifier,
    uiState: ApplicationUiState
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppbar(
                navigationIcon = {
                    Icon(
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
            uiState = uiState
        )
    }

}

@Composable
fun ApplicationScreenContent(
    modifier: Modifier = Modifier,
    uiState: ApplicationUiState
) {

    LazyColumn(
        modifier = modifier
            .padding(CustomTheme.spaces.medium)
    ){
        items(uiState.applications.size){
            ApplicationCard(
                modifier = Modifier
                    .clickable {

                    },
                application = uiState.applications[it]
            )
            SpacerHeight(size = CustomTheme.spaces.small)
        }
    }
}
