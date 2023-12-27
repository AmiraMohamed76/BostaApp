package com.amira.bostaapp.data.remote

import com.amira.bostaapp.data.remote.dto.AlbumDto
import com.amira.bostaapp.data.remote.dto.CommonResponse
import com.amira.bostaapp.data.remote.dto.ImageDto
import com.amira.bostaapp.data.remote.dto.UserDto
import retrofit2.Response
import retrofit2.http.*

interface ServiceApi {

    @GET("users")
    suspend fun getUser(): Response<List<UserDto>>

    @GET("albums")
    suspend fun getAlbums(
         @Query("userId") userId: Int,
    ): Response<List<AlbumDto>>

    @GET("photos")
    suspend fun getAlbumImage(
        @Query("albumId") albumId: Int,
    ): Response<List<ImageDto>>


}