package com.amira.bostaapp.domain.model

data class User(
    var userId: Int = 0,
    var name: String = "",
    var userName: String = "",
    var email: String = "",
    var address: Address,
    var phone: String = "",
    var website: String = "",
    var company: Company,
)
