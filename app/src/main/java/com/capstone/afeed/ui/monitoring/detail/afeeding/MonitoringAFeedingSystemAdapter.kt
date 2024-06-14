package com.capstone.afeed.ui.monitoring.detail.afeeding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.response.GetListAFeedingFromFishPondIDResponse
import com.capstone.afeed.databinding.ItemCardIotSystemInfoBinding

class MonitoringAFeedingSystemAdapter :
    ListAdapter<GetListAFeedingFromFishPondIDResponse.AFeedingSystem, MonitoringAFeedingSystemAdapter.ViewHolder>(DIFF_CALLBACK) {
    inner class ViewHolder(val binding: ItemCardIotSystemInfoBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetListAFeedingFromFishPondIDResponse.AFeedingSystem, position: Int) {
            with(binding){
                textViewTitle.setText(R.string.a_feeding_system)
                textViewStatus.text = item.status
                textViewIdSystem.text = item.idTopicMqtt
                textViewTitleScore.setText(context.getString(R.string.feeding_progress))
                textViewScore.text = item.feedingProgress
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardIotSystemInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),position)
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<GetListAFeedingFromFishPondIDResponse.AFeedingSystem>() {
                override fun areItemsTheSame(
                    oldItem: GetListAFeedingFromFishPondIDResponse.AFeedingSystem,
                    newItem: GetListAFeedingFromFishPondIDResponse.AFeedingSystem
                ): Boolean {
                    return oldItem.idAFeeding == newItem.idAFeeding
                }

                override fun areContentsTheSame(
                    oldItem: GetListAFeedingFromFishPondIDResponse.AFeedingSystem,
                    newItem: GetListAFeedingFromFishPondIDResponse.AFeedingSystem
                ): Boolean {
                    return oldItem.idAFeeding == newItem.idAFeeding
                }

            }
    }
}