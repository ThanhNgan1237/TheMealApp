package com.ngan.themealapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ngan.themealapp.data.model.MealDetail
import com.ngan.themealapp.data.repository.MealRepository
import com.ngan.themealapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : BaseViewModel() {

    private val _favoriteMeals = MutableLiveData<List<MealDetail>>()
    val favoriteMeals: LiveData<List<MealDetail>>
        get() = _favoriteMeals

    fun getAllFavoriteMeals() {
        viewModelScope.launch (Dispatchers.IO){
            mealRepository.getAllFavoriteMeal().also { _favoriteMeals.postValue(it) }
        }
    }
}