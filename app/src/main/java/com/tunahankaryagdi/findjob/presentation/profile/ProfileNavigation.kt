package com.tunahankaryagdi.findjob.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tunahankaryagdi.findjob.presentation.home.HomeScreenRoute
import com.tunahankaryagdi.findjob.presentation.home.homeRoute


const val profileRoute = "profile"


fun NavController.navigateToProfile(
    navOptions: NavOptions? = null
){
    this.navigate(profileRoute,navOptions)
}

fun NavGraphBuilder.profileScreen(){
    composable(profileRoute){
        ProfileScreenRoute()
    }
}

