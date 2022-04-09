package com.ngan.themealapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ngan.themealapp.databinding.FragmentListMealBinding
import com.ngan.themealapp.ui.adapter.MealAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListMealFragment : Fragment(), MealAdapter.CallbackToListFragment {

    private val viewModel by viewModels<ListMealViewModel>()
    private lateinit var binding: FragmentListMealBinding
    private val args: ListMealFragmentArgs by navArgs()
    private val adapter = MealAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListMealBinding.inflate(layoutInflater, container, false)
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
            textCategoryTitle.text = args.category
            adapter.callback = this@ListMealFragment

            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initObserver() {
        viewModel.apply {
            mealsByCategory.observe(viewLifecycleOwner, {
                it.forEach { mealByCategory ->
                    mealByCategory.category = args.category
                }
                adapter.setData(it)
            })
        }
    }

    private fun initData() {
        viewModel.apply {
            getMealsByCategory(args.category)
        }
    }

    override fun onClickMealItem(id: String) {
        findNavController().navigate(ListMealFragmentDirections.actionToMealDetail(id))
    }
}