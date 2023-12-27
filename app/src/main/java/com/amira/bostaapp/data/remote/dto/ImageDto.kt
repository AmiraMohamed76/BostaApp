package com.amira.bostaapp.data.remote.dto

import com.amira.bostaapp.data.local.entities.ImageTable
import com.amira.bostaapp.domain.model.Image
import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("albumId") var albumId: Int = 0,
    @SerializedName("id") var imageId: Int = 0,
    @SerializedName("title") var title: String = "",
    @SerializedName("url") var url: String = "",
    @SerializedName("thumbnailUrl") var thumbnailUrl: String = "",

) {
    fun imageDtoToImageTable(): ImageTable {
        return ImageTable(
            albumId = albumId,
            imageId = imageId,
            title = title,
            url = url,
            thumbnailUrl = thumbnailUrl
        )
    }
}

fun ImageTable.imageDtoToImage(): Image {
    return Image(
        albumId = albumId,
        imageId = imageId,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}