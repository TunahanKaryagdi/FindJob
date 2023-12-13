package com.tunahankaryagdi.findjob.presentation.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val homeRoute = "home"

fun NavController.navigateToHome(navOptions: NavOptions? = null){
    this.navigate(homeRoute,navOptions)
}

fun NavGraphBuilder.homeScreen(
    navigateToDetail : () -> Unit
){

    composable(homeRoute){
        HomeScreenRoute(
            navigateToDetail = navigateToDetail
        )
    }
}


