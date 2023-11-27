package com.tunahankaryagdi.findjob.presentation.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val detailRoute = "detail"


fun NavController.navigateToDetail(
    navOptions: NavOptions? = null
){
    this.navigate(detailRoute,navOptions = navOptions)
}

fun NavGraphBuilder.detailScreen(){
    composable(detailRoute){
        DetailScreenRoute()
    }
}