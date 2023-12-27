package com.amira.bostaapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amira.bostaapp.domain.model.Address
import com.amira.bostaapp.domain.model.Company
import com.amira.bostaapp.domain.model.Geometric

@Entity(tableName = "AddressTable")
data class GeometricTable(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "lat") var street: String = "",
    @ColumnInfo(name = "lng") var suite: String = "",
)