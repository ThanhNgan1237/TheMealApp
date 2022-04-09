package com.ngan.themealapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ngan.themealapp.data.model.MealDetail

@Dao
interface MealDao {

    @Insert
    suspend fun addFavorite(mealDetail: MealDetail)

    @Query("DELETE FROM mealDetailTbl WHERE idMeal = :id")
    suspend fun removeFavorite(id: Int)

    @Query("SELECT * FROM mealDetailTbl")
    fun getAllFavoriteMeal(): List<MealDetail>

    @Query("SELECT * FROM mealDetailTbl WHERE idMeal = :id")
    suspend fun isSaved(id: Int): MealDetail
}