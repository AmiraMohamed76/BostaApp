package com.amira.bostaapp.domain.use_cases

import com.amira.bostaapp.domain.use_cases.local.GetLocalAlbumsUseCase
import com.amira.bostaapp.domain.use_cases.local.GetLocalImagesUseCase
import com.amira.bostaapp.domain.use_cases.local.GetLocalUserDataUseCase
import com.amira.bostaapp.domain.use_cases.remote.GetRemoteAlbumsUseCase
import com.amira.bostaapp.domain.use_cases.remote.GetRemoteImagesUseCase
import com.amira.bostaapp.domain.use_cases.remote.GetRemoteUserDataUseCase

data class UseCases(
    var albumsRemoteUseCase: GetRemoteAlbumsUseCase,
    var userRemoteDataUseCase: GetRemoteUserDataUseCase,
    var userLocalDataUseCase: GetLocalUserDataUseCase,
    var albumsLocalUseCase: GetLocalAlbumsUseCase,
    var imagesRemoteUseCase: GetRemoteImagesUseCase,
    var imagesLocalUseCase: GetLocalImagesUseCase,
)
