package com.capstone.afeed.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.capstone.afeed.R

fun ImageView.loadImage(data: Int) {
    Glide.with(this.context).load(data).centerCrop().into(this)
}

fun ImageView.loadImage(data: String) {
    Glide.with(this.context).load(data).error(R.drawable.baseline_image_24).into(this)
}