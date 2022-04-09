package com.ngan.themealapp.data.repository

import com.ngan.themealapp.data.dao.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllCategories() = apiService.getAllCategories()

    
}