package com.tunahankaryagdi.findjob.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tunahankaryagdi.findjob.presentation.apply.applyScreen
import com.tunahankaryagdi.findjob.presentation.apply.navigateToApply
import com.tunahankaryagdi.findjob.presentation.detail.detailScreen
import com.tunahankaryagdi.findjob.presentation.detail.navigateToDetail
import com.tunahankaryagdi.findjob.presentation.home.homeRoute
import com.tunahankaryagdi.findjob.presentation.home.homeScreen
import com.tunahankaryagdi.findjob.presentation.login.loginScreen
import com.tunahankaryagdi.findjob.presentation.signup.signupRoute
import com.tunahankaryagdi.findjob.presentation.signup.signupScreen


@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {

    NavHost(
        navController = navController ,
        startDestination = startDestination,
    ){
        loginScreen(navigateToSignup = {navController.navigate(signupRoute)})

        signupScreen(navigateToHome = {navController.navigate(homeRoute)})

        homeScreen(navigateToDetail =  {navController.navigateToDetail()})

        detailScreen(navigateToApply = {navController.navigateToApply()})

        applyScreen()
    }

}

