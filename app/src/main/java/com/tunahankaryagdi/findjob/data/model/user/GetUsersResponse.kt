package com.tunahankaryagdi.findjob.data.model.user

import com.tunahankaryagdi.findjob.data.model.user.dtos.UserDetailDto

data class GetUsersResponse(
    val data: List<UserDetailDto>,
    val message: String,
    val success: Boolean
)