package com.tunahankaryagdi.findjob.presentation.apply

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val applyRoute = "apply"

fun NavController.navigateToApply(
    navOptions: NavOptions? = null
){
    this.navigate(applyRoute,navOptions)
}

fun NavGraphBuilder.applyScreen() {
    composable(applyRoute){
        ApplyScreenRoute()
    }
}