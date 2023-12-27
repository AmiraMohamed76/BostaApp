package com.amira.bostaapp.data

/** Class helper to hold all states for the result of fetching a remote api service.*/
sealed class Resource<T>(
    val data: T? = null,
    val exception: com.amira.bostaapp.data.Error? = null
) {
    /** Success Data response. */
    class Success<T>(data: T?) : Resource<T>(data)

    /** Failure response. */
    class Error<T>(exception: com.amira.bostaapp.data.Error? = null) :
        Resource<T>(exception = exception)

    /** Loading indicator while a request is in progress state.*/
    //class Loading<T> : Resource<T>()
}

data class Error(
    var errorCode: Int = 0,
    var errorMessage: String? = "Some error occurred, trt again"
) : Exception()
