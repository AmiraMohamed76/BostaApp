package com.amira.bostaapp.domain.use_cases.remote

import android.util.Log
import com.amira.bostaapp.data.Resource
import com.amira.bostaapp.domain.model.Album
import com.amira.bostaapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRemoteAlbumsUseCase @Inject constructor(
    private val repository: Repository,
) {
    operator fun invoke(userId:Int): Flow<Resource<Boolean>> = flow {
        try {
            repository.getRemoteAlbums(userId = userId).let { response ->
                Log.d("Response", response.body().toString())
                val albumList = response.body()?.map { albumDto ->
                    albumDto.albumDtoToAlbumTable()
                }
                albumList?.let { repository.insertAlbums(it) }
                emit(Resource.Success(true))
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