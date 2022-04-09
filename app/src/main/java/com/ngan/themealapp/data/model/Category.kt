package com.ngan.themealapp.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory")
    var id: String,
    @SerializedName("strCategory")
    var name: String,
    @SerializedName("strCategoryThumb")
    var image: String,
)
