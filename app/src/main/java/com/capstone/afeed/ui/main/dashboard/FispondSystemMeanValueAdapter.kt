package com.capstone.afeed.ui.main.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.afeed.data.remote.response.GetListRegisteredFishpondWithSystemMeanScore
import com.capstone.afeed.databinding.ItemCardFishpondWithMeanSystemValueBinding
import com.capstone.afeed.databinding.ItemCardIotSystemInfoBinding

class FispondSystemMeanValueAdapter : ListAdapter<GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData, FispondSystemMeanValueAdapter.Viewholder>(DIFF_CALLBACK) {
    inner class Viewholder(val binding :ItemCardFishpondWithMeanSystemValueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData) {
            with(binding){
                textViewFishpondName.text = item.fishpondName
                textViewFishpondDescription.text = item.fishpondDescription
                textViewTotalPh.text = item.phScoreTotal
                textViewTotalTemperature.text = item.temperatureScoreTotal
                textViewFeedingProgress.text = item.aFeedingProgress
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ItemCardFishpondWithMeanSystemValueBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData>(){
            override fun areItemsTheSame(
                oldItem: GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData,
                newItem: GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData
            ): Boolean {
                return oldItem.idFishpond == newItem.idFishpond
            }

            override fun areContentsTheSame(
                oldItem: GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData,
                newItem: GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData
            ): Boolean {
                return oldItem.idFishpond == newItem.idFishpond
            }

        }
    }


}