package com.amira.bostaapp.data.repository

import com.amira.bostaapp.data.local.AppDao
import com.amira.bostaapp.data.local.entities.AlbumTable
import com.amira.bostaapp.data.local.entities.ImageTable
import com.amira.bostaapp.data.local.entities.UserTable
import com.amira.bostaapp.data.remote.ServiceApi
import com.amira.bostaapp.data.remote.dto.AlbumDto
import com.amira.bostaapp.data.remote.dto.ImageDto
import com.amira.bostaapp.data.remote.dto.UserDto
import com.amira.bostaapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class RepositoryImpl(private val appDao: AppDao, private val api: ServiceApi) : Repository {
    override suspend fun getRemoteUsers(): Response<List<UserDto>> {
        return api.getUser()
    }
    override suspend fun getRemoteAlbums(userId: Int) :Response<List<AlbumDto>>{
        return api.getAlbums(userId = userId)
    }
    override suspend fun getRemoteImages(albumId:Int) :Response<List<ImageDto>>{
        return api.getAlbumImage(albumId = albumId)
    }

    override suspend fun getLocalUser(randomId: Int): Flow<List<UserTable>> {
        return appDao.selectUser(randomId = randomId)
    }

    override suspend fun getLocalAlbums(): Flow<List<AlbumTable>> {
        return appDao.selectAlbums()
    }

    override suspend fun getLocalImages(): Flow<List<ImageTable>> {
        return appDao.selectImages()
    }

    override suspend fun insertUsers(usersList: List<UserTable>) {
        appDao.deleteAndInsertUserTable(user = usersList)
    }

    override suspend fun insertAlbums(albumsList: List<AlbumTable>) {
        appDao.deleteAndInsertAlbumsTable(albums = albumsList)
    }

    override suspend fun insertImages(imagesList: List<ImageTable>) {
        appDao.deleteAndInsertImagesTable(images = imagesList)
    }

}