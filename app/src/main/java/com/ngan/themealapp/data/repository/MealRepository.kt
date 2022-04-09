package com.ngan.themealapp.data.repository

import com.ngan.themealapp.data.dao.ApiService
import com.ngan.themealapp.data.dao.MealDao
import com.ngan.themealapp.data.model.MealDetail
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val apiService: ApiService,
    private val mealDao: MealDao
) {
    suspend fun getRandomMeal() = apiService.getRandomMeal()

    suspend fun getMealsByCategory(category: String) = apiService.getMealsByCategory(category)

    suspend fun getMealDetailById(id: String) = apiService.getMealDetail(id)

    suspend fun insertFavorite(mealDetail: MealDetail) = mealDao.addFavorite(mealDetail)

    suspend fun removeFavorite(id: Int) = mealDao.removeFavorite(id)

    suspend fun getAllFavoriteMeal() = mealDao.getAllFavoriteMeal()

    suspend fun checkFavorite(id: Int) = mealDao.isSaved(id)

    suspend fun getMealByName(name:String) = apiService.getMealByName(name)
}