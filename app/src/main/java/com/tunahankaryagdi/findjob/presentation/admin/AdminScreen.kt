package com.tunahankaryagdi.findjob.presentation.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun AdminScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: AdminViewModel = hiltViewModel()
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    AdminScreen(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
fun AdminScreen(
    modifier: Modifier = Modifier,
    uiState: AdminUiState
) {
    AdminScreenContent(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
fun AdminScreenContent(
    modifier: Modifier = Modifier,
    uiState: AdminUiState
) {


}