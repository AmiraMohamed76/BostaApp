package com.amira.bostaapp.data.remote.dto

import com.amira.bostaapp.data.local.entities.AlbumTable
import com.amira.bostaapp.domain.model.Album
import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("userId") var userId: Int = 0,
    @SerializedName("id") var albumId: Int = 0,
    @SerializedName("title") var title: String = "",
) {
    fun albumDtoToAlbumTable(): AlbumTable {
        return AlbumTable(
            userId = userId,
            albumId = albumId,
            title = title
        )
    }
}

fun AlbumTable.albumTableToAlbumItem(): Album {
    return Album(
        userId = userId,
        albumId = albumId,
        title = title
    )
}