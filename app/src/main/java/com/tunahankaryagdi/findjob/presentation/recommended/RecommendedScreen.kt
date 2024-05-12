package com.tunahankaryagdi.findjob.presentation.recommended

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.components.CustomTopAppbar
import com.tunahankaryagdi.findjob.ui.theme.CustomTheme
import com.tunahankaryagdi.findjob.utils.TabItem


@Composable
fun RecommendedScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: RecommendedViewModel = hiltViewModel()
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(null)

    RecommendedScreen(
        modifier = modifier,
        uiState = uiState,
        onTrigger = viewModel::handleEvents
    )
}

@Composable
fun RecommendedScreen(
    modifier: Modifier = Modifier,
    uiState: RecommendedUiState,
    onTrigger: (RecommendedEvent) -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppbar(
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
            onTrigger = onTrigger
        )
    }



}

@Composable
fun RecommendedScreenContent(
    modifier: Modifier = Modifier,
    uiState: RecommendedUiState,
    onTrigger: (RecommendedEvent) -> Unit
) {


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
        when(uiState.tabIndex){
            0 ->{
                ProfileRecommendationScreen(
                    modifier = modifier,
                )
            }
            1 ->{
                AppliedJobRecommendationScreen(
                    modifier = modifier
                )
            }
        }

    }
}

@Composable
fun ProfileRecommendationScreen(
    modifier: Modifier = Modifier
) {

}

@Composable
fun AppliedJobRecommendationScreen(
    modifier: Modifier = Modifier
) {

}

