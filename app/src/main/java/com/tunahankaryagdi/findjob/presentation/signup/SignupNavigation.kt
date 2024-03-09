package com.tunahankaryagdi.findjob.presentation.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val signupRoute = "signup"

fun NavGraphBuilder.signupScreen(
    navigateToLogin: () -> Unit
){
    composable(signupRoute){
        SignupScreenRoute(
            navigateToLogin = navigateToLogin
        )
    }
}