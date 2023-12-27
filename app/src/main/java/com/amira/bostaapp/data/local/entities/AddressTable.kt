package com.amira.bostaapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amira.bostaapp.domain.model.Address
import com.amira.bostaapp.domain.model.Company
import com.amira.bostaapp.domain.model.Geometric

@Entity(tableName = "AddressTable")
data class AddressTable(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "street") var street: String = "",
    @ColumnInfo(name = "suite") var suite: String = "",
    @ColumnInfo(name = "city") var city: String = "",
    @ColumnInfo(name = "zipcode") var zipcode: String = "",
    @ColumnInfo(name = "geo") var geo:Geometric,

)