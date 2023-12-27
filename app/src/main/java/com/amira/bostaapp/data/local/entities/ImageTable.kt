package com.amira.bostaapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ImageTable")
data class ImageTable(
    @PrimaryKey var imageId: Int = 0,
    @ColumnInfo(name = "albumId") var albumId: Int = 0,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "url") var url: String = "",
    @ColumnInfo(name = "thumbnailUrl") var thumbnailUrl: String = "",
)