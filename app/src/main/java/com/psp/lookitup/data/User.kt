package com.psp.lookitup.data

import java.text.DateFormat

data class User(

    val id: Int,
    val name: String = "",
    var emailId: String = "",
    val DOB: String = "",
    val gender: String= "",
    val Status: String = "",
    val occupation: String = "",
    val location: String = "",

    )

