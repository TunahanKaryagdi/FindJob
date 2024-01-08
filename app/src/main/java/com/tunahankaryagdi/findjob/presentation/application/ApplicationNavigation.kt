package com.tunahankaryagdi.findjob.presentation.application

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val applicationRoute = "application"

fun NavController.navigateToApplication(
    jobId: String,
    navOptions: NavOptions? = null
){
    this.navigate("$applicationRoute/$jobId",navOptions)
}

fun NavGraphBuilder.applicationScreen(){
    composable("$applicationRoute/{jobId}"){
        ApplicationScreenRoute()
    }
}