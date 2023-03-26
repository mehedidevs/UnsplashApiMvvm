package com.example.unsplashapimvvm.repo

import com.example.unsplashapimvvm.apiservices.*
import com.example.unsplashapimvvm.model.PhotoModel
import com.example.unsplashapimvvm.utils.ApiDataState
import com.example.unsplashapimvvm.utils.UiDataState
import com.example.unsplashapimvvm.utils.StringUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class ImagineRepositoryImpl @Inject constructor(
    private val apiService: UnsplashApiService,
    private val stringUtils: StringUtils,
) : ImagineRepository {


    override suspend fun loadPhotos(
        pageNumber: Int,
        pageSize: Int,
        orderBy: String
    ): Flow<ApiDataState<List<PhotoModel>>> {

        return flow {
            apiService.loadPhotos(pageNumber, pageSize, orderBy).apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(ApiDataState.success(it))
                    }
                }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
            }.onErrorSuspend {
                emit(ApiDataState.error<List<PhotoModel>>(message()))

                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(ApiDataState.error<List<PhotoModel>>(stringUtils.noNetworkErrorMessage()))
                } else {
                    emit(ApiDataState.error<List<PhotoModel>>(stringUtils.somethingWentWrong()))
                }
            }


        }
    }

    override suspend fun searchPhotos(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): Flow<UiDataState<List<PhotoModel>>> {
        TODO("Not yet implemented")
    }
}



