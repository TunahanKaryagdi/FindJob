package com.tunahankaryagdi.findjob.utils

import com.tunahankaryagdi.findjob.R

enum class TabItem (
    val tabName: String,
    val icon: Int
){

    Profile(
        "Profile",
        R.drawable.ic_user
    ),
    Applied(
    "AppliedJobs",
    R.drawable.ic_upload
    )
}