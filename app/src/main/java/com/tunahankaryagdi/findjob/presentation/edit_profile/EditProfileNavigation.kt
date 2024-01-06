package com.tunahankaryagdi.findjob.presentation.edit_profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val editProfileRoute = "editProfile"

fun NavController.navigateToEditProfile(
    navOptions: NavOptions? = null
){
    this.navigate(editProfileRoute,navOptions)
}

fun NavGraphBuilder.editProfileScreen(){
    composable(editProfileRoute){
        EditProfileScreenRoute()
    }
}