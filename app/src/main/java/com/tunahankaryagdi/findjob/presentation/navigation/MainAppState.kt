package com.tunahankaryagdi.findjob.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.tunahankaryagdi.findjob.presentation.add.navigateToAdd
import com.tunahankaryagdi.findjob.presentation.home.homeRoute
import com.tunahankaryagdi.findjob.presentation.home.navigateToHome
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberMainAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController : NavHostController  = rememberNavController()
) : MainAppState {
    return remember(coroutineScope,navController){
        MainAppState(navController,coroutineScope)
    }
}



@Stable
class MainAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
){

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination


    private val topLevelDestinations = TopLevelDestination.values().toList()


    val shouldShowBottomBar: Boolean
        @Composable get() = currentDestination?.hierarchy?.any { destination ->
            topLevelDestinations.any {
                destination.route?.contains(it.route) ?: false
            }
        } ?: false

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelOptions = navOptions {
            popUpTo(homeRoute) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToHome(topLevelOptions)
            TopLevelDestination.ADD -> navController.navigateToAdd(topLevelOptions)

        }
    }
}