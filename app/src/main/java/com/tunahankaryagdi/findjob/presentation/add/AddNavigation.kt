package com.tunahankaryagdi.findjob.presentation.add

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val addRoute = "add"


fun NavGraphBuilder.addScreen(modifier: Modifier = Modifier){
    composable(addRoute){
        AddScreenRoute()
    }
}