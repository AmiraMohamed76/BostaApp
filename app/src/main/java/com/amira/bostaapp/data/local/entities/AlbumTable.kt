package com.amira.bostaapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "AlbumTable")
data class AlbumTable(

    @PrimaryKey var albumId: Int = 0,
    @ColumnInfo(name = "userId") var userId: Int = 0,
    @ColumnInfo(name = "title") var title: String = "",
)