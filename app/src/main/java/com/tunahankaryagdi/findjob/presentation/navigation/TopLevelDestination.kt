package com.tunahankaryagdi.findjob.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.add.addRoute
import com.tunahankaryagdi.findjob.presentation.home.homeRoute

enum class TopLevelDestination(
    val route: String,
    val icon: ImageVector,
    val textId: Int
){

    HOME(
        route = homeRoute,
        icon = Icons.Default.Home,
        textId = R.string.home
    ),

    ADD(
        route = addRoute,
        icon = Icons.Default.Add,
        textId = R.string.add_job
    )
}