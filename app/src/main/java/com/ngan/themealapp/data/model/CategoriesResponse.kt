package com.ngan.themealapp.data.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("categories")
    val categories: List<Category>
)
