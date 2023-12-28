package com.tunahankaryagdi.findjob.presentation.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val splashRoute = "splash"


fun NavGraphBuilder.splashScreen(
    navigateToLogin: ()->Unit,
    navigateToHome: ()->Unit,
){
    composable(splashRoute){
        SplashScreenRoute(
            navigateToLogin = navigateToLogin,
            navigateToHome = navigateToHome
        )
    }
}



