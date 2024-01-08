package com.tunahankaryagdi.findjob.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tunahankaryagdi.findjob.presentation.splash.splashRoute


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    appState: MainAppState = rememberMainAppState()
) {

     Scaffold(
        modifier = modifier,
        bottomBar = {
            AnimatedVisibility(visible = appState.shouldShowBottomBar) {
                AppBottomNavBar(
                    destinations = TopLevelDestination.values().toList(),
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }
        }
    ) {
        NavigationHost(
            modifier = Modifier.padding(it),
            navController = appState.navController,
            startDestination = splashRoute
        )
    }

}
