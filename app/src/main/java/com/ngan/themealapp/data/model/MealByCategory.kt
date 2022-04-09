package com.ngan.themealapp.data.model

import com.google.gson.annotations.SerializedName

class MealByCategory(
    @SerializedName("strMeal")
    var name: String,
    @SerializedName("strMealThumb")
    var image: String,
    @SerializedName("idMeal")
    var id: String
) {
    var category: String = ""
}
