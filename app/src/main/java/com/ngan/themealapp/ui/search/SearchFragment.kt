package com.ngan.themealapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ngan.themealapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    private val adapter = MealSearchAdapter().apply {
        callback = object : MealSearchAdapter.CallbackToListFragment{
            override fun onClickMealItem(id: String) {
                    findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToMealDetailFragment(id))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitView()
        observeData()
    }

    private fun onInitView() {
        with(binding){
            recyclerMeals.adapter = adapter
            edtSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!v.text.toString().isNullOrEmpty()) {
                        viewModel.getMealsByName(v.text.toString())
                    }
                    return@OnEditorActionListener true
                }
                false
            })
        }
    }

    private fun observeData() {
        viewModel.apply {
            mealsByName.observe(viewLifecycleOwner) {
                adapter.setData(it)
            }
        }
    }
}
