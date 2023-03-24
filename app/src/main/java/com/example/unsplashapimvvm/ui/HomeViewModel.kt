package com.example.unsplashapimvvm.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplashapimvvm.model.PhotoModel
import com.example.unsplashapimvvm.repo.ImagineRepositoryImpl
import com.example.unsplashapimvvm.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var imagineRepositoryImpl: ImagineRepositoryImpl
) : ViewModel() {

    private var _photoList = MutableLiveData<DataState<List<PhotoModel>>>()
    val photoList: LiveData<DataState<List<PhotoModel>>>
        get() = _photoList




     fun fetchPhotos(page: Int) {

        viewModelScope.launch {
            imagineRepositoryImpl.loadPhotos(page, 30, "popular").collect { dataState ->

                Log.i("TAG", "fetchPhotos: $dataState")


                when (dataState) {
                    is DataState.Error -> {
                        _photoList.postValue(DataState.Error(dataState.message))

                    }
                    is DataState.Loading -> {
                        _photoList.postValue(DataState.Loading("Loading...."))
                    }
                    is DataState.Success -> {

                        _photoList.postValue(DataState.Success(dataState.data))
                    }
                }


            }


        }


    }


}