package com.tunahankaryagdi.findjob.presentation.apply

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val applyRoute = "apply"

fun NavController.navigateToApply(
    jobId: String,
    navOptions: NavOptions? = null
){
    this.navigate("$applyRoute/$jobId",navOptions)
}

fun NavGraphBuilder.applyScreen() {
    composable("$applyRoute/{jobId}"){
        ApplyScreenRoute()
    }
}