package com.amira.bostaapp.domain.repository

import com.amira.bostaapp.data.local.entities.AlbumTable
import com.amira.bostaapp.data.local.entities.ImageTable
import com.amira.bostaapp.data.local.entities.UserTable
import com.amira.bostaapp.data.remote.dto.AlbumDto
import com.amira.bostaapp.data.remote.dto.ImageDto
import com.amira.bostaapp.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun getRemoteUsers(): Response<List<UserDto>>
    suspend fun getRemoteAlbums(userId:Int): Response<List<AlbumDto>>
    suspend fun getRemoteImages(albumId:Int): Response<List<ImageDto>>
    suspend fun getLocalUser(randomId : Int): Flow<List<UserTable>>
    suspend fun getLocalAlbums(): Flow<List<AlbumTable>>
    suspend fun getLocalImages(): Flow<List<ImageTable>>
    suspend fun insertUsers(usersList: List<UserTable>)
    suspend fun insertAlbums(albumsList: List<AlbumTable>)
    suspend fun insertImages(imagesList: List<ImageTable>)

}