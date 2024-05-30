package com.capstone.afeed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.afeed.data.ImageDataSlide
import com.capstone.afeed.databinding.ItemSlideBinding
import com.capstone.afeed.utils.loadImage

class ImageSliderAdapter(private val imageDataSlide: List<ImageDataSlide>) :
    RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {
    class ViewHolder(itemView: ItemSlideBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: ImageDataSlide) {
            binding.ivItem.loadImage(data.imageData)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = imageDataSlide.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageDataSlide[position])
    }
}