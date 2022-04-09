package com.ngan.themealapp.data.model

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("meals")
    val mealDetails: List<MealDetail>
)
