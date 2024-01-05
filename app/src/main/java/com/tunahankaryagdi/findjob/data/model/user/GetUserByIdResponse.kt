package com.tunahankaryagdi.findjob.data.model.user

import com.tunahankaryagdi.findjob.data.model.user.dtos.UserDetailDto

data class GetUserByIdResponse(
    val data: UserDetailDto,
    val message: String,
    val success: Boolean
)





