package com.tunahankaryagdi.findjob.presentation.jobs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val jobsRoute = "jobs"

fun NavController.navigateToJobs(navOptions: NavOptions? = null){
    this.navigate(jobsRoute,navOptions)
}


fun NavGraphBuilder.jobScreen(
    navigateToApplication: (String) -> Unit,
    navigatePop: () -> Unit,
){
    composable(jobsRoute){
        JobScreenRoute(
            navigateToApplication = navigateToApplication,
            navigatePop = navigatePop
        )
    }
}