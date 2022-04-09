package com.ngan.themealapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "mealDetailTbl")
class MealDetail(
    @SerializedName("strMeal")
    @ColumnInfo("strMeal")
    val name: String,
    @SerializedName("idMeal")
    val id: String,
    @ColumnInfo("idMeal")
    @PrimaryKey(autoGenerate = true)
    var idFavorite: Int,
    @SerializedName("strCategory")
    @ColumnInfo("strCategory")
    val category: String,
    @SerializedName("strInstructions")
    @ColumnInfo("strInstructions")
    val instruction: String,
    @SerializedName("strArea")
    @ColumnInfo("strArea")
    val area: String,
    @SerializedName("strMealThumb")
    @ColumnInfo("strMealThumb")
    val image: String,
    @SerializedName("strYoutube")
    @ColumnInfo("strYoutube")
    val linkYt: String
) {
    var isFavorite = false
}
