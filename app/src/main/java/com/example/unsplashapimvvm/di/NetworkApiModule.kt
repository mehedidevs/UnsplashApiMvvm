package com.example.unsplashapimvvm.di

import com.example.unsplashapimvvm.apiservices.ApiResponseCallAdapterFactory
import com.example.unsplashapimvvm.apiservices.UnsplashApiService
import com.example.unsplashapimvvm.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkApiModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest =
                    request.newBuilder().header("Authorization", AppConstants.API.API_KEY)
                chain.proceed(newRequest.build())
            }
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UnsplashApiService.BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesUnsplashApiService(retrofit: Retrofit): UnsplashApiService {
        return retrofit.create(UnsplashApiService::class.java)
    }
}
