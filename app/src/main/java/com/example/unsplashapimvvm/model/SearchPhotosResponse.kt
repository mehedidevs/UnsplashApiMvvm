
package com.example.unsplashapimvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class SearchPhotosResponse(
    @Expose @SerializedName("total") val total: Int,
    @Expose @SerializedName("total_pages") val totalPages: Int,
    @Expose @SerializedName("results") val photosList: List<PhotoModel>
)
