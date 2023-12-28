package com.tunahankaryagdi.findjob.presentation.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val signupRoute = "signup"

fun NavGraphBuilder.signupScreen(
    navigateToHome : ()->Unit,
    navigateToLogin: () -> Unit
){
    composable(signupRoute){
        SignupScreenRoute(
            navigateToHome = navigateToHome,
            navigateToLogin = navigateToLogin
        )
    }
}