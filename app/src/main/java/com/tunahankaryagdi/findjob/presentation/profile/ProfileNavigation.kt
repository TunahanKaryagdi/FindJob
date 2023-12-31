package com.tunahankaryagdi.findjob.presentation.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val profileRoute = "profile"


fun NavController.navigateToProfile(
    navOptions: NavOptions? = null
){
    this.navigate(profileRoute,navOptions)
}

fun NavGraphBuilder.profileScreen(
    navigateToEditProfile: () -> Unit
){
    composable(profileRoute){
        ProfileScreenRoute(
            navigateToEditProfile = navigateToEditProfile
        )
    }
}

