package com.tunahankaryagdi.findjob.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.tunahankaryagdi.findjob.presentation.add.addScreen
import com.tunahankaryagdi.findjob.presentation.add_company.addCompanyScreen
import com.tunahankaryagdi.findjob.presentation.add_company.navigateToAddCompany
import com.tunahankaryagdi.findjob.presentation.admin.adminScreen
import com.tunahankaryagdi.findjob.presentation.application.applicationScreen
import com.tunahankaryagdi.findjob.presentation.application.navigateToApplication
import com.tunahankaryagdi.findjob.presentation.my_applications.myApplicationsScreen
import com.tunahankaryagdi.findjob.presentation.my_applications.navigateToMyApplications
import com.tunahankaryagdi.findjob.presentation.apply.applyScreen
import com.tunahankaryagdi.findjob.presentation.apply.navigateToApply
import com.tunahankaryagdi.findjob.presentation.detail.detailScreen
import com.tunahankaryagdi.findjob.presentation.detail.navigateToDetail
import com.tunahankaryagdi.findjob.presentation.edit_profile.editProfileScreen
import com.tunahankaryagdi.findjob.presentation.edit_profile.navigateToEditProfile
import com.tunahankaryagdi.findjob.presentation.home.homeRoute
import com.tunahankaryagdi.findjob.presentation.home.homeScreen
import com.tunahankaryagdi.findjob.presentation.home.navigateToHome
import com.tunahankaryagdi.findjob.presentation.jobs.jobScreen
import com.tunahankaryagdi.findjob.presentation.jobs.navigateToJobs
import com.tunahankaryagdi.findjob.presentation.login.loginRoute
import com.tunahankaryagdi.findjob.presentation.login.loginScreen
import com.tunahankaryagdi.findjob.presentation.login.navigateToLogin
import com.tunahankaryagdi.findjob.presentation.profile.navigateToProfile
import com.tunahankaryagdi.findjob.presentation.profile.profileScreen
import com.tunahankaryagdi.findjob.presentation.signup.signupRoute
import com.tunahankaryagdi.findjob.presentation.signup.signupScreen
import com.tunahankaryagdi.findjob.presentation.splash.splashRoute
import com.tunahankaryagdi.findjob.presentation.splash.splashScreen


@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {

    NavHost(
        modifier = modifier,
        navController = navController ,
        startDestination = startDestination,
    ){


        splashScreen(
            navigateToLogin = {navController.navigateToLogin(
                navOptions {
                    popUpTo(splashRoute) {
                        inclusive = true
                    }
                }
            )},
            navigateToHome = {navController.navigateToHome(
                navOptions {
                    popUpTo(splashRoute) {
                        inclusive = true
                    }
                }
            )}
        )

        loginScreen(
            navigateToSignup = {navController.navigate(signupRoute)},
            navigateToHome = {navController.navigateToHome(
                navOptions {
                    popUpTo(loginRoute) {
                        inclusive = true
                    }
                }
            )}
        )

        signupScreen(
            navigateToLogin = {navController.navigateToLogin()}
        )

        homeScreen(
            navigateToDetail =  {navController.navigateToDetail(it)},
        )

        detailScreen(
            navigateToApply = {navController.navigateToApply(it)},
            navigatePop = {navController.popBackStack()}
        )

        applyScreen()

        addScreen(
            navigateToAddCompany = {navController.navigateToAddCompany()}
        )

        profileScreen(
            navigateToEditProfile = {navController.navigateToEditProfile()},
            navigateToLogin = {navController.navigateToLogin(
                navOptions {
                    popUpTo(homeRoute) {
                        inclusive = true
                    }
                }
            )},
            navigatePop = {navController.popBackStack()}
        )

        editProfileScreen(
            navigatePop = {navController.popBackStack()}
        )

        myApplicationsScreen(
            navigateToDetail = {navController.navigateToDetail(it)},
            navigatePop = {navController.popBackStack()}
        )

        jobScreen(
            navigateToApplication = {navController.navigateToApplication(it)},
            navigatePop = {navController.popBackStack()}
        )

        applicationScreen(
            navigatePop = {navController.popBackStack()}
        )

        adminScreen()

        addCompanyScreen()
    }

}

