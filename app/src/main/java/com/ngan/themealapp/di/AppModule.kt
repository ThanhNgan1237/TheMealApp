package com.ngan.themealapp.di

import android.content.Context
import androidx.room.Room
import com.ngan.themealapp.data.dao.AppDatabase
import com.ngan.themealapp.data.dao.ApiService
import com.ngan.themealapp.data.dao.MealDao
import com.ngan.themealapp.data.repository.CategoryRepository
import com.ngan.themealapp.data.repository.MealRepository
import com.ngan.themealapp.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    private fun getOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        return builder.addInterceptor {
            val request = it.request().newBuilder()
                .build()
            it.proceed(request)

        }.addInterceptor(httpLoggingInterceptor)
            .build()

    }

    @Singleton
    @Provides
    fun retrofitBuilder() = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun roomBuilder(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "MealDB"
    ).build().getMealDao()

    @Singleton
    @Provides
    fun provideCategoryRepository(apiService: ApiService) = CategoryRepository(apiService)

    @Singleton
    @Provides
    fun provideMealRepository(apiService: ApiService, mealDao: MealDao) =
        MealRepository(apiService, mealDao)
}