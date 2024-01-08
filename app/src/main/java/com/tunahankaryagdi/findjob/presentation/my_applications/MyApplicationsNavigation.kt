package com.tunahankaryagdi.findjob.presentation.my_applications

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val myApplicationsRoute = "myApplications"


fun NavController.navigateToMyApplications(
    navOptions: NavOptions? = null
){
    this.navigate(myApplicationsRoute,navOptions)
}

fun NavGraphBuilder.myApplicationsScreen(
    navigateToDetail: (String) -> Unit
) {
    composable(myApplicationsRoute){
        MyApplicationsScreenRoute(
            navigateToDetail = navigateToDetail
        )
    }
}

