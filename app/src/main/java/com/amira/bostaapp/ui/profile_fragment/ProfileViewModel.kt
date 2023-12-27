package com.amira.bostaapp.ui.profile_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amira.bostaapp.data.remote.UiState
import com.amira.bostaapp.domain.model.Album
import com.amira.bostaapp.domain.model.User
import com.amira.bostaapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {
    private val _albumsRemoteLiveData = MutableLiveData<UiState<Boolean>>()
    val albumsRemoteLiveData: LiveData<UiState<Boolean>> = _albumsRemoteLiveData

    private val _usersRemoteLiveData = MutableLiveData<UiState<Boolean>>()
    val usersRemoteLiveData: LiveData<UiState<Boolean>> = _usersRemoteLiveData

    private val _imagesRemoteLiveData = MutableLiveData<UiState<Boolean>>()
    val imagesRemoteLiveData: LiveData<UiState<Boolean>> = _imagesRemoteLiveData

    private val _usersLocalLiveData = MutableLiveData<UiState<List<User>>>()
    val usersLocalLiveData: LiveData<UiState<List<User>>> = _usersLocalLiveData

    private val _albumsLocalLiveData = MutableLiveData<UiState<List<Album>>>()
    val albumsLocalLiveData: LiveData<UiState<List<Album>>> = _albumsLocalLiveData

    init {
        getRemoteUsers()
    }
    private fun getRemoteUsers() {
        viewModelScope.launch {
            useCases.userRemoteDataUseCase.invoke().collect { response ->
                when {
                    response.exception != null -> {
                        _usersRemoteLiveData.value =
                            UiState(exception = response.exception.errorMessage.toString())
                    }
                    response.data == true -> {
                        _usersRemoteLiveData.value = UiState(data = response.data)
                    }
                }
            }
        }
    }

    fun getRemoteImages(albumId:Int) {
        viewModelScope.launch {
            useCases.imagesRemoteUseCase.invoke(albumId = albumId).collect { response ->
                when {
                    response.exception != null -> {
                        _imagesRemoteLiveData.value =
                            UiState(exception = response.exception.errorMessage.toString())
                    }
                    response.data == true -> {
                        _imagesRemoteLiveData.value = UiState(data = response.data)
                    }
                }
            }
        }
    }

    fun getRemoteAlbums(userId: Int) {
        viewModelScope.launch {
            useCases.albumsRemoteUseCase.invoke(userId= userId).collect { response ->
                when {
                    response.exception != null -> {
                        _albumsRemoteLiveData.value =
                            UiState(exception = response.exception.errorMessage.toString())
                    }
                    response.data == true -> {
                        _albumsRemoteLiveData.value = UiState(data = response.data)
                    }
                }
            }
        }
    }

    fun getLocalUsers(userId:Int) {
        viewModelScope.launch {
            useCases.userLocalDataUseCase.invoke(userId = userId).collect { response ->
                when {
                    response.exception != null -> {
                        _usersLocalLiveData.value =
                            UiState(exception = response.exception.errorMessage.toString())
                    }
                    response.data != null -> {
                        _usersLocalLiveData.value = UiState(data = response.data)
                    }
                }
            }
        }
    }

    fun getLocalAlbum() {
        viewModelScope.launch {
            useCases.albumsLocalUseCase.invoke().collect { response ->
                when {
                    response.exception != null -> {
                        _albumsLocalLiveData.value =
                            UiState(exception = response.exception.errorMessage.toString())
                    }
                    response.data != null -> {
                        _albumsLocalLiveData.value = UiState(data = response.data)
                    }
                }
            }
        }
    }
}