package com.amira.bostaapp.data.local

import androidx.room.*
import com.amira.bostaapp.data.local.entities.AlbumTable
import com.amira.bostaapp.data.local.entities.ImageTable
import com.amira.bostaapp.data.local.entities.UserTable
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Transaction
    suspend fun deleteAndInsertUserTable(user: List<UserTable>) {
        deleteUserTable()
        insertUserTable(user)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserTable(user: List<UserTable>)

    @Query("Delete From UserTable")
    suspend fun deleteUserTable()

    @Query("SELECT * From UserTable Where userId =:randomId")
    fun selectUser(randomId:Int): Flow<List<UserTable>>

    @Transaction
    suspend fun deleteAndInsertAlbumsTable(albums: List<AlbumTable>) {
        deleteAlbumsTable()
        insertAlbumsTable(albums)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbumsTable(albums: List<AlbumTable>)

    @Query("Delete From AlbumTable")
    suspend fun deleteAlbumsTable()

    @Query("SELECT * From AlbumTable")
    fun selectAlbums(): Flow<List<AlbumTable>>

    @Transaction
    suspend fun deleteAndInsertImagesTable(images: List<ImageTable>) {
        deleteImagesTable()
        insertImagesTable(images)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImagesTable(images: List<ImageTable>)

    @Query("Delete From ImageTable")
    suspend fun deleteImagesTable()

    @Query("SELECT * From ImageTable")
    fun selectImages(): Flow<List<ImageTable>>



}