package com.example.unsplashapimvvm.repo


import com.example.unsplashapimvvm.model.PhotoModel
import com.example.unsplashapimvvm.utils.ApiDataState
import com.example.unsplashapimvvm.utils.UiDataState
import kotlinx.coroutines.flow.Flow

interface ImagineRepository {
    suspend fun loadPhotos(
        pageNumber: Int,
        pageSize: Int,
        orderBy: String
    ): Flow<ApiDataState<List<PhotoModel>>>

    suspend fun searchPhotos(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): Flow<UiDataState<List<PhotoModel>>>
}
