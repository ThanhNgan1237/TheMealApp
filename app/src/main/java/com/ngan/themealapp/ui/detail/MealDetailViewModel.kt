package com.ngan.themealapp.ui.detail

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
class MealDetailViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : BaseViewModel() {

    private val _mealDetail = MutableLiveData<MealDetail>()
    val mealDetail: LiveData<MealDetail>
        get() = _mealDetail

    private val _mealDetailFavorite = MutableLiveData<MealDetail>()
    val mealDetailFavorite: LiveData<MealDetail>
        get() = _mealDetailFavorite

    fun getMealDetailById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.getMealDetailById(id)
                .also { _mealDetail.postValue(it.mealDetails.first()) }
        }
    }

    fun checkFavorite(id: Int) {
        viewModelScope.launch (Dispatchers.IO){
            mealRepository.checkFavorite(id).also { _mealDetailFavorite.postValue(it) }
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.removeFavorite(id)
        }
    }

    fun insertFavorite(mealDetail: MealDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.insertFavorite(mealDetail)
        }
    }
}