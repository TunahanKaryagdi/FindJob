package com.tunahankaryagdi.findjob.presentation.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val homeRoute = "home"

fun NavGraphBuilder.homeScreen(){

    composable(homeRoute){
        HomeScreen()
    }
}