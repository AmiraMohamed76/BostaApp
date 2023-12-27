package com.amira.bostaapp.ui.album_images_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amira.bostaapp.data.remote.UiState
import com.amira.bostaapp.domain.model.Album
import com.amira.bostaapp.domain.model.Image
import com.amira.bostaapp.domain.model.User
import com.amira.bostaapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumImagesViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private val _imageLocalLiveData = MutableLiveData<UiState<List<Image>>>()
    val imageLocalLiveData: LiveData<UiState<List<Image>>> = _imageLocalLiveData

    init {
        getLocalImages()
    }
    private fun getLocalImages() {
        viewModelScope.launch {
            useCases.imagesLocalUseCase.invoke().collect { response ->
                when {
                    response.exception != null -> {
                        _imageLocalLiveData.value =
                            UiState(exception = response.exception.errorMessage.toString())
                    }
                    response.data != null -> {
                        _imageLocalLiveData.value = UiState(data = response.data)
                    }
                }
            }
        }
    }
}