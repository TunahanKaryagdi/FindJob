package com.tunahankaryagdi.findjob.utils

import com.auth0.android.jwt.Claim
import com.auth0.android.jwt.JWT
import java.util.Date

object JwtHelper{

    fun getUserId(token: String) : String?{
        val jwt = JWT(token)
        return jwt.getClaim("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier").asString()
    }

    fun isTokenValid(token: String): Boolean {
        val expDate = JWT(token).expiresAt
        val currentDate = Date()
        return currentDate.before(expDate)
    }
}