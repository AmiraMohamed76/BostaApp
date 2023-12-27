package com.amira.bostaapp.domain.use_cases.remote

import android.util.Log
import com.amira.bostaapp.data.Resource
import com.amira.bostaapp.domain.model.Album
import com.amira.bostaapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRemoteImagesUseCase @Inject constructor(
    private val repository: Repository,
) {
    operator fun invoke(albumId:Int): Flow<Resource<Boolean>> = flow {
        try {
            repository.getRemoteImages(albumId = albumId).let { response ->
                val imageList = response.body()?.map { imageDto ->
                    imageDto.imageDtoToImageTable()
                }
                imageList?.let { repository.insertImages(it) }
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