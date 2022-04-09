package com.ngan.themealapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ngan.themealapp.data.model.Category
import com.ngan.themealapp.data.model.MealDetail
import com.ngan.themealapp.data.repository.CategoryRepository
import com.ngan.themealapp.data.repository.MealRepository
import com.ngan.themealapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val mealRepository: MealRepository
) : BaseViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _randomMeal = MutableLiveData<MealDetail>()
    val randomMeal: LiveData<MealDetail>
        get() = _randomMeal

    fun getAllCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.getAllCategories().also { _categories.postValue(it.categories) }
        }
    }

    fun getRandomMeal() {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.getRandomMeal().also { _randomMeal.postValue(it.mealDetails[0]) }
        }
    }

}