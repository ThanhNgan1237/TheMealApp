package com.ngan.themealapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ngan.themealapp.R
import com.ngan.themealapp.databinding.FragmentHomeBinding
import com.ngan.themealapp.ui.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() , CategoryAdapter.CallbackToHomeFragment {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        initData()
        onRandomMealClick()
    }

    private fun initView() {
        adapter = CategoryAdapter().apply { callback =  this@HomeFragment}
        binding.recyclerCategories.adapter = adapter
    }

    private fun initObserver() {
        viewModel.apply {
            randomMeal.observe(viewLifecycleOwner , {
                binding.apply {
                    context?.let { it1 ->
                        Glide.with(it1)
                            .load(it.image)
                            .error(R.drawable.ic_launcher_background)
                            .into(imageMeal)
                    }
                }
            })

            categories.observe(viewLifecycleOwner, {
                adapter.setData(it)
            })
        }
    }

    private fun initData() {
        viewModel.apply {
            getRandomMeal()
            getAllCategories()
        }
    }

    override fun onClickItem(category: String) {
        findNavController().navigate(HomeFragmentDirections.actionToListmeal(category))
    }

    fun  onRandomMealClick(){
        binding.imageMeal.setOnClickListener{
            val action = HomeFragmentDirections.actionToMealDetail(viewModel.randomMeal.value?.id.toString())
            findNavController().navigate(action)
        }
    }

}