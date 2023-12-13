package com.tunahankaryagdi.findjob.presentation.add

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val addRoute = "add"

fun NavController.navigateToAdd(navOptions: NavOptions? = null){
    this.navigate(addRoute,navOptions)
}


fun NavGraphBuilder.addScreen(modifier: Modifier = Modifier){
    composable(addRoute){
        AddScreenRoute()
    }
}