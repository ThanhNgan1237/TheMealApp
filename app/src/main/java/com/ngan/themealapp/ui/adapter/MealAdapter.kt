package com.ngan.themealapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngan.themealapp.R
import com.ngan.themealapp.data.model.MealByCategory
import com.ngan.themealapp.databinding.ItemMealListBinding

class MealAdapter: RecyclerView.Adapter<MealAdapter.ViewHolder>() {

    val meals = mutableListOf<MealByCategory>()

    fun setData(meals : List<MealByCategory>) {
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

        fun bindView(mealByCategory: MealByCategory) {
            binding.apply {
                textMealName.text = mealByCategory.name
                textCategoryMeal.text = mealByCategory.category
                Glide.with(root.context)
                    .load(mealByCategory.image)
                    .error(R.mipmap.ic_launcher)
                    .into(imageMealItem)
            }
            itemView.setOnClickListener {
                callback?.onClickMealItem(mealByCategory.id)
            }
        }
    }

    interface CallbackToListFragment {
        fun onClickMealItem(id : String)
    }

    var callback: CallbackToListFragment? = null
}