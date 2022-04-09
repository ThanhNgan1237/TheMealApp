package com.ngan.themealapp.data.dao

import com.ngan.themealapp.data.model.CategoriesResponse
import com.ngan.themealapp.data.model.MealByCategoryResponse
import com.ngan.themealapp.data.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    suspend fun getAllCategories(): CategoriesResponse

    @GET("random.php")
    suspend fun getRandomMeal(): MealResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") name: String): MealByCategoryResponse

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") id: String): MealResponse

    @GET("search.php")
    suspend fun getMealByName(@Query("s") name: String): MealResponse

}