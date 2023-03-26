package com.example.unsplashapimvvm.utils


sealed class UiDataState<T> {
    data class Success<T>(val data: T) : UiDataState<T>()
    data class Error<T>(val message: String) : UiDataState<T>()
    data class Loading<T>(val message: String) : UiDataState<T>()
}
