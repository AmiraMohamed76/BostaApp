package com.amira.bostaapp.data.remote

data class UiState<T>(
    val loading: Boolean=false, val data: T?=null, var exception: String=""
)