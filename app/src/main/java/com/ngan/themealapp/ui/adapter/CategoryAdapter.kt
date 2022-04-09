package com.ngan.themealapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ngan.themealapp.R
import com.ngan.themealapp.data.model.Category
import com.ngan.themealapp.databinding.ItemCategoryBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val categories = mutableListOf<Category>()

    fun setData(categories: List<Category>) {
        this.categories.apply {
            clear()
            addAll(categories)
            notifyDataSetChanged()
        }
    }

    fun setData(){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding , callback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(categories[position])
    }

    override fun getItemCount() = categories.size

    class ViewHolder(
        private val binding: ItemCategoryBinding,
        private val callbackToHomeFragment: CallbackToHomeFragment?
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindView(category: Category) {
            binding.apply {
                textCategory.text = category.name
                Glide.with(root.context)
                    .load(category.image)
                    .error(R.mipmap.ic_launcher)
                    .into(imageCategory)
            }
            itemView.setOnClickListener {
                callbackToHomeFragment?.onClickItem(category.name)
            }
        }
    }

    interface CallbackToHomeFragment {
        fun onClickItem(category: String)
    }


    var callback: CallbackToHomeFragment? = null
}