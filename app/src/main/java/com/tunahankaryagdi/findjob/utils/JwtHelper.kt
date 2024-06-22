package com.tunahankaryagdi.findjob.utils

import com.auth0.android.jwt.JWT
import java.util.Date

object JwtHelper{

    fun getUserId(token: String) : String?{
        val jwt = JWT(token)
        return jwt.getClaim("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier").asString()
    }

    fun getUserRoles(token: String) : List<String>{
        val jwt = JWT(token)
        return jwt.getClaim("http://schemas.microsoft.com/ws/2008/06/identity/claims/role").asList(String::class.java)
    }


    fun isTokenValid(token: String): Boolean {
        val expDate = JWT(token).expiresAt
        val currentDate = Date()
        return currentDate.before(expDate)
    }
}