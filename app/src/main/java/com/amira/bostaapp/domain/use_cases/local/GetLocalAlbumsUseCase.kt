package com.amira.bostaapp.domain.use_cases.local

import android.util.Log
import com.amira.bostaapp.data.Resource
import com.amira.bostaapp.data.remote.dto.albumTableToAlbumItem
import com.amira.bostaapp.data.remote.dto.userTableToUser
import com.amira.bostaapp.domain.model.Album
import com.amira.bostaapp.domain.model.User
import com.amira.bostaapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocalAlbumsUseCase @Inject constructor(
    private val repository: Repository,
) {
    operator fun invoke(): Flow<Resource<List<Album>>> = flow {
        try {
            repository.getLocalAlbums().collect{
                val list = it.map { albumTable ->
                    albumTable.albumTableToAlbumItem()
                }
                emit(Resource.Success(data = list))
            }
        } catch (exception: Exception) {
            Log.d("exception", exception.toString())

            emit(
                Resource.Error(
                    exception = com.amira.bostaapp.data.Error(
                        0,
                        exception.localizedMessage
                    )
                )
            )
        }
    }
}