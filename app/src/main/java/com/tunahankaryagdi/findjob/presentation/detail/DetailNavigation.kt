package com.tunahankaryagdi.findjob.presentation.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val detailRoute = "detail"


fun NavController.navigateToDetail(
    jobId: String,
    navOptions: NavOptions? = null
){
    this.navigate("$detailRoute/$jobId",navOptions = navOptions)
}

fun NavGraphBuilder.detailScreen(
    navigateToApply : (String) -> Unit,
    navigatePop: () -> Unit
){
    composable("$detailRoute/{jobId}"){
        DetailScreenRoute(
            navigateToApply = navigateToApply,
            navigatePop = navigatePop
        )
    }
}