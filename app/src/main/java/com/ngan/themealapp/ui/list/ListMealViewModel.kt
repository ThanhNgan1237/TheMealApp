package com.ngan.themealapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ngan.themealapp.data.model.MealByCategory
import com.ngan.themealapp.data.model.MealDetail
import com.ngan.themealapp.data.repository.MealRepository
import com.ngan.themealapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMealViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : BaseViewModel() {

    private val _mealsByCategory = MutableLiveData<List<MealByCategory>>()
    val mealsByCategory: LiveData<List<MealByCategory>>
        get() = _mealsByCategory

    fun getMealsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.getMealsByCategory(category)
                .also { _mealsByCategory.postValue(it.meals) }
        }
    }
}