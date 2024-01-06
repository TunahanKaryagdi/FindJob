package com.tunahankaryagdi.findjob.presentation.applications

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.tunahankaryagdi.findjob.presentation.apply.ApplyScreenContent
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme


@Composable
fun ApplicationsScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: ApplicationsViewModel = hiltViewModel()
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    ApplicationsScreen(
        modifier = modifier,
        uiState = uiState
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationsScreen(
    modifier: Modifier = Modifier,
    uiState: ApplicationsUiState

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
                        text = stringResource(id = R.string.applications),
                        style = CustomTheme.typography.titleNormal
                    )
                }
            )
        },
        containerColor = CustomTheme.colors.primaryBackground
    ){
        ApplicationsScreenContent(
            modifier = modifier.padding(it),
            uiState = uiState
        )
    }


}



@Composable
fun ApplicationsScreenContent(
    modifier: Modifier = Modifier,
    uiState: ApplicationsUiState
) {


    LazyColumn(
        modifier = modifier
    ){
        items(uiState.applications.size){

        }
    }
}