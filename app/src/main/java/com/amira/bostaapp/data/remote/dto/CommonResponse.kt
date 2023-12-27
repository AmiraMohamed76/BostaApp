package com.amira.bostaapp.data.remote.dto

import com.google.gson.annotations.SerializedName

class CommonResponse<T> {
    @SerializedName("response")
    var serviceResponse: ServiceResponse<T>? = null
}

class ServiceResponse<T> {
    @SerializedName("data")
    var data: T? = null
}

class UpdateResponse {
    @SerializedName("result")
    var result: Boolean? = null
}