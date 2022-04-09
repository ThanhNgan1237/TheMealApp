package com.ngan.themealapp.ui.detail

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ngan.themealapp.R
import com.ngan.themealapp.data.model.MealDetail
import com.ngan.themealapp.databinding.FragmentMealDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.net.Uri


@AndroidEntryPoint
class MealDetailFragment : Fragment() {

    private val viewModel by viewModels<MealDetailViewModel>()
    private val args: MealDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentMealDetailBinding
    private var mealDetail: MealDetail? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        initData()
    }

    private fun initData() {
        viewModel.getMealDetailById(args.idMeal)
    }

    private fun initObserver() {
        viewModel.apply {
            mealDetail.observe(viewLifecycleOwner, {
                this@MealDetailFragment.mealDetail = it
                viewModel.checkFavorite(Integer.parseInt(it.id))
                binding.apply {
                    context?.let { it1 ->
                        Glide.with(it1)
                            .load(it.image)
                            .error(R.mipmap.ic_launcher)
                            .into(imageMeal)
                    }
                    textMealName.text = it.name
                    textDescription.text = it.linkYt
                    textInstruction.text = it.instruction
                }
            })

            mealDetailFavorite.observe(viewLifecycleOwner, {
                binding.imageFavorite.setImageResource(if (it == null) R.drawable.ic_favorite_white else R.drawable.ic_favorite)
                if (it != null) {
                    this@MealDetailFragment.mealDetail?.isFavorite = true
                }
            })
        }
    }

    private fun initView() {
        binding.apply {
            textDescription.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            imageFavorite.setOnClickListener {
                if (mealDetail?.isFavorite == true) {
                    imageFavorite.setImageResource(R.drawable.ic_favorite_white)
                    viewModel.removeFavorite((Integer.parseInt(mealDetail?.id)))
                } else {
                    imageFavorite.setImageResource(R.drawable.ic_favorite)
                    mealDetail?.idFavorite = Integer.parseInt(mealDetail?.id)
                    mealDetail?.let { it1 -> viewModel.insertFavorite(it1) }
                }
                mealDetail?.isFavorite = !mealDetail?.isFavorite!!

            }

            textDescription.setOnClickListener {
                cardWebView.visibility = View.VISIBLE
                webYoutube.apply {
                    settings.javaScriptEnabled = true
                    settings.setSupportZoom(true)
                    canGoBack()
                    mealDetail?.let {
                        if (it.linkYt.contains("youtube")) {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.linkYt)))
                        } else {
                            loadUrl(it.linkYt)
                        }
                    }
                    cardWebView.visibility = View.GONE
                }
            }
        }
    }
}