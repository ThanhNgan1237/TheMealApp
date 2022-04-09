package com.ngan.themealapp.ui.search

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
class SearchViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : BaseViewModel(){

    private val _mealsByName = MutableLiveData<List<MealDetail>>()
    val mealsByName: LiveData<List<MealDetail>>
        get() = _mealsByName

    fun getMealsByName(name:String) {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.getMealByName(name).also { _mealsByName.postValue(it.mealDetails)
            }
        }
    }
}