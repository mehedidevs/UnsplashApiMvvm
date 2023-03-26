package com.example.unsplashapimvvm.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashapimvvm.model.PhotoModel
import com.example.unsplashapimvvm.repo.ImagineRepositoryImpl
import com.example.unsplashapimvvm.utils.ApiDataState
import com.example.unsplashapimvvm.utils.UiDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var imagineRepositoryImpl: ImagineRepositoryImpl
) : ViewModel() {

    private var _photoList = MutableLiveData<UiDataState<List<PhotoModel>>>()
    val photoList: LiveData<UiDataState<List<PhotoModel>>>
        get() = _photoList

    init {
        _photoList.postValue(UiDataState.Loading("Loading"))

    }


    fun fetchPhotos(page: Int) {

        viewModelScope.launch {
            imagineRepositoryImpl.loadPhotos(page, 30, "popular").collect { apiState ->


                when (apiState) {
                    is ApiDataState.Success -> {
                        _photoList.postValue(UiDataState.Success(apiState.data))
                    }

                    is ApiDataState.Error -> {
                        _photoList.postValue(UiDataState.Error(apiState.message))
                    }


                }


            }


        }


    }


}