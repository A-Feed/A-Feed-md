package com.capstone.afeed.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(data: Int) {
    Glide.with(this.context).load(data).centerCrop().into(this)
}