package com.tunahankaryagdi.findjob.data.model.company

import java.io.File


data class PostCompanyRequest(
    val name: String,
    val file: File
)