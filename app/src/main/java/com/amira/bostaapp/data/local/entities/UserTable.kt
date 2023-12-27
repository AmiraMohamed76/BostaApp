package com.amira.bostaapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amira.bostaapp.domain.model.Address
import com.amira.bostaapp.domain.model.Company

@Entity(tableName = "UserTable")
data class UserTable(
    @PrimaryKey
    var userId: Int = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "userName") var userName: String = "",
    @ColumnInfo(name = "email") var email: String = "",
    @ColumnInfo(name = "address") var address: Address,
    @ColumnInfo(name = "phone") var phone: String = "",
    @ColumnInfo(name = "website") var website: String = "",
    @ColumnInfo(name = "company") var company: Company,
)