package com.ngan.themealapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngan.themealapp.R
import com.ngan.themealapp.data.model.MealByCategory
import com.ngan.themealapp.data.model.MealDetail
import com.ngan.themealapp.databinding.ItemMealListBinding

class MealSearchAdapter: RecyclerView.Adapter<MealSearchAdapter.ViewHolder>() {

    val meals = mutableListOf<MealDetail>()

    fun setData(meals : List<MealDetail>) {
        this.meals.apply {
            clear()
            addAll(meals)
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMealListBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding , callback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(meals[position])
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    class ViewHolder(
        private val binding: ItemMealListBinding,
        private val callback: CallbackToListFragment?
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindView(mealDetail: MealDetail) {
            binding.apply {
                textMealName.text = mealDetail.name
                textCategoryMeal.text = mealDetail.category
                Glide.with(root.context)
                    .load(mealDetail.image)
                    .error(R.mipmap.ic_launcher)
                    .into(imageMealItem)
            }
            itemView.setOnClickListener {
                callback?.onClickMealItem(mealDetail.id)
            }
        }
    }

    interface CallbackToListFragment {
        fun onClickMealItem(id : String)
    }

    var callback: CallbackToListFragment? = null
}