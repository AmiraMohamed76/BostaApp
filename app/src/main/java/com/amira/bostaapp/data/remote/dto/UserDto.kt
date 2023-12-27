package com.amira.bostaapp.data.remote.dto

import com.amira.bostaapp.data.local.entities.UserTable
import com.amira.bostaapp.domain.model.Address
import com.amira.bostaapp.domain.model.Company
import com.amira.bostaapp.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id") var userId: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("username") var userName: String = "",
    @SerializedName("email") var email: String = "",
    @SerializedName("address") var address: Address,
    @SerializedName("phone") var phone: String = "",
    @SerializedName("website") var website: String = "",
    @SerializedName("company") var company: Company,
) {
    fun userDtoToUserTable(): UserTable {
        return UserTable(
            userId = userId,
            name = name,
            userName = userName,
            email = email,
            address = address,
            phone = phone,
            website = website,
            company = company
        )
    }
}

fun UserTable.userTableToUser(): User {
    return User(
        userId = userId,
        name = name,
        userName = userName,
        email = email,
        address = address,
        phone = phone,
        website = website,
        company = company
    )
}