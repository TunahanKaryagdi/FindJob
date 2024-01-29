package com.tunahankaryagdi.findjob.presentation.admin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val adminRoute = "admin"

fun NavController.navigateToAdmin(
    navOptions: NavOptions? = null
){
    this.navigate(adminRoute,navOptions)
}

fun NavGraphBuilder.adminScreen(){
    composable(adminRoute){
        AdminScreenRoute()
    }
}