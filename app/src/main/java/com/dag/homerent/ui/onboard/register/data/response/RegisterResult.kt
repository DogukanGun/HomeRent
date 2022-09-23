package com.dag.homerent.ui.onboard.register.data.response

data class RegisterResult(
    var id:Int = 0,
    var name:String = "",
    var username:String = "",
    var email:String = "",
    var phoneNumber:String = "",
    var userType:String = "",
    var userPhoto:String = ""
)