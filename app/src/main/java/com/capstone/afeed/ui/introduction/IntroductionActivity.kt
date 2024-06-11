package com.capstone.afeed.ui.introduction

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.afeed.R
import com.capstone.afeed.adapter.ImageSliderAdapter
import com.capstone.afeed.data.ImageDataSlide
import com.capstone.afeed.databinding.ActivityIntroductionBinding
import com.capstone.afeed.ui.main.MainActivity

class IntroductionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroductionBinding
    private lateinit var adapter: ImageSliderAdapter
    private val listImage = ArrayList<ImageDataSlide>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listImage.addAll(getListImageIntroduction())
        adapter = ImageSliderAdapter(listImage)
        binding.vpImageSlider.adapter = adapter

        binding.tvSkip.setOnClickListener {
            Intent(this, MainActivity::class.java).also { intent ->
                startActivity(intent)
                finish()
            }
        }
    }

    private fun getListImageIntroduction(): ArrayList<ImageDataSlide> {
        val list = ArrayList<ImageDataSlide>()

        val imageResourceIds = listOf(
            R.drawable.frame_0,
            R.drawable.frame_1,
            R.drawable.frame_2,
            R.drawable.frame_3
        )

        for (imageResourceId in imageResourceIds) {
            val imageDataSlide = ImageDataSlide(imageData = imageResourceId)
            list.add(imageDataSlide)
        }

        return list
    }
}