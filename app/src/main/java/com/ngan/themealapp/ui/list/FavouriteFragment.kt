package com.ngan.themealapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ngan.themealapp.data.model.MealByCategory
import com.ngan.themealapp.data.model.MealDetail
import com.ngan.themealapp.databinding.FragmentFavouriteBinding
import com.ngan.themealapp.ui.adapter.MealAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() , MealAdapter.CallbackToListFragment{

    private lateinit var binding: FragmentFavouriteBinding
    private val viewModel by viewModels<FavoriteViewModel>()
    private val adapter = MealAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        initData()
    }

    private fun initView() {
        binding.apply {
            recyclerMeals.adapter = adapter
            adapter.callback = this@FavouriteFragment
        }
    }

    private fun initObserver() {
        viewModel.favoriteMeals.observe(viewLifecycleOwner , {
            val meals = mutableListOf<MealByCategory>()
            it.forEach {
                meals.add(MealByCategory(it.name , it.image , it.id))
            }
            adapter.setData(meals)
        })
    }

    private fun initData() {
        viewModel.getAllFavoriteMeals()
    }

    override fun onClickMealItem(id: String) {
        findNavController().navigate(FavouriteFragmentDirections.actionFromFavoriteToMealDetail(id))
    }
}