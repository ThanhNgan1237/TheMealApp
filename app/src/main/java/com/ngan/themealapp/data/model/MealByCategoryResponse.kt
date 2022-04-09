package com.ngan.themealapp.data.model

import com.google.gson.annotations.SerializedName

data class MealByCategoryResponse(
    @SerializedName("meals")
    val meals: List<MealByCategory>
)
