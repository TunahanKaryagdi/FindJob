package com.tunahankaryagdi.findjob.presentation.applications

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val applicationsRoute = "applications"


fun NavController.navigateToApplications(
    navOptions: NavOptions? = null
){
    this.navigate(applicationsRoute,navOptions)
}

fun NavGraphBuilder.applicationsScreen(
    navigateToDetail: (String) -> Unit
) {
    composable(applicationsRoute){
        ApplicationsScreenRoute(
            navigateToDetail = navigateToDetail
        )
    }
}

