package com.psp.lookitup.data

import java.text.DateFormat

data class User(

    val id: Int,
    val name: String? = null,
    val emailId: String? = null,
    val DOB: DateFormat? = null,
    val gender: String?= null,
    val Status: String? = null,
    val occupation: String? = null,
    val location: String? = null

)

