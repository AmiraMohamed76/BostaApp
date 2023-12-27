package com.amira.bostaapp.domain.use_cases.remote

import android.util.Log
import com.amira.bostaapp.data.Resource
import com.amira.bostaapp.data.remote.dto.userTableToUser
import com.amira.bostaapp.domain.model.User
import com.amira.bostaapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRemoteUserDataUseCase @Inject constructor(
    private val repository: Repository,
) {
    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        try {
            repository.getRemoteUsers().let { response ->
                Log.d("Response", response.body().toString())
                val userList = response.body()?.map { userDto ->
                    userDto.userDtoToUserTable()
                }
                userList?.let { repository.insertUsers(it) }
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