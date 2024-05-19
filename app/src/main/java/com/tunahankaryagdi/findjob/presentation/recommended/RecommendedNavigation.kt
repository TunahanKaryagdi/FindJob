package com.tunahankaryagdi.findjob.presentation.recommended

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val recommendedRoute = "recommended"

fun NavController.navigateToRecommended(navOptions: NavOptions? = null){
    this.navigate(recommendedRoute,navOptions)
}


fun NavGraphBuilder.recommendedScreen(
    navigateToDetail: (String) -> Unit,
    navigatePop: () -> Unit,
){
    composable(recommendedRoute){
        RecommendedScreenRoute(
            navigateToDetail = navigateToDetail,
            navigatePop = navigatePop
        )
    }
}