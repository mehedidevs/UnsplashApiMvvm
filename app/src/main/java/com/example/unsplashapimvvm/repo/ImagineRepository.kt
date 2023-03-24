package com.example.unsplashapimvvm.repo


import com.example.unsplashapimvvm.model.PhotoModel
import com.example.unsplashapimvvm.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ImagineRepository {
    suspend fun loadPhotos(
        pageNumber: Int,
        pageSize: Int,
        orderBy: String
    ): Flow<DataState<List<PhotoModel>>>

    suspend fun searchPhotos(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): Flow<DataState<List<PhotoModel>>>
}
