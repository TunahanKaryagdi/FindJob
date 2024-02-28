package com.tunahankaryagdi.findjob.presentation.add_company

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val addCompanyRoute = "addCompany"

fun NavController.navigateToAddCompany(navOptions: NavOptions? = null){
    this.navigate(addCompanyRoute,navOptions)
}

fun NavGraphBuilder.addCompanyScreen(){
    composable(addCompanyRoute){
        AddCompanyRoute()
    }
}