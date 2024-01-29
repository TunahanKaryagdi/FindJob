package com.tunahankaryagdi.findjob.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.tunahankaryagdi.findjob.R
import com.tunahankaryagdi.findjob.presentation.add.addRoute
import com.tunahankaryagdi.findjob.presentation.home.homeRoute
import com.tunahankaryagdi.findjob.presentation.jobs.jobsRoute
import com.tunahankaryagdi.findjob.presentation.my_applications.myApplicationsRoute
import com.tunahankaryagdi.findjob.presentation.profile.profileRoute

enum class TopLevelDestination(
    val route: String,
    val painterId: Int,
    val textId: Int
){

    HOME(
        route = homeRoute,
        painterId = R.drawable.ic_home,
        textId = R.string.home
    ),

    APPLICATIONS(
        route = myApplicationsRoute,
        painterId = R.drawable.ic_applications,
        textId = R.string.applications
    ),

    ADD(
        route = addRoute,
        painterId = R.drawable.ic_add,
        textId = R.string.add_job
    ),

    MY_JOBS(
        route = jobsRoute,
        painterId = R.drawable.ic_job,
        textId = R.string.jobs
    ),

    PROFILE(
        route = profileRoute,
        painterId = R.drawable.ic_user,
        textId = R.string.profile
    ),
}