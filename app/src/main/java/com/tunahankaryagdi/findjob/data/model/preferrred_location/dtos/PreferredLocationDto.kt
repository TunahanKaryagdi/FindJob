package com.tunahankaryagdi.findjob.data.model.preferrred_location.dtos

import com.tunahankaryagdi.findjob.domain.model.user.PreferredLocation

data class PreferredLocationDto(
    val userId: String,
    val name: String
)

fun PreferredLocationDto.toPreferredLocation() : PreferredLocation{
    return PreferredLocation(
        userId, name
    )
}
