package com.capstone.afeed.ui.monitoring.detail.phsystem

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.response.GetListAFeedingFromFishPondIDResponse
import com.capstone.afeed.data.remote.response.GetListPhSystemFromFishPondIDResponse
import com.capstone.afeed.databinding.ItemCardIotSystemInfoBinding

class MonitoringPhSystemAdapter : ListAdapter<GetListPhSystemFromFishPondIDResponse.Data,MonitoringPhSystemAdapter.ViewHolder>(
    DIFF_CALLBACK) {
    inner class ViewHolder(val binding: ItemCardIotSystemInfoBinding,val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetListPhSystemFromFishPondIDResponse.Data, position: Int) {
            with(binding){
                textViewTitle.setText(R.string.ph_system)
                textViewStatus.text = item.status
                textViewIdSystem.text = item.idTopicMqtt
                textViewTitleScore.setText(context.getString(R.string.ph_total_score))
                textViewScore.text = item.phScore
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
            object : DiffUtil.ItemCallback<GetListPhSystemFromFishPondIDResponse.Data>() {
                override fun areItemsTheSame(
                    oldItem: GetListPhSystemFromFishPondIDResponse.Data,
                    newItem: GetListPhSystemFromFishPondIDResponse.Data
                ): Boolean {
                    return oldItem.idPhSystem == newItem.idPhSystem
                }

                override fun areContentsTheSame(
                    oldItem: GetListPhSystemFromFishPondIDResponse.Data,
                    newItem: GetListPhSystemFromFishPondIDResponse.Data
                ): Boolean {
                    return oldItem.idPhSystem == newItem.idPhSystem
                }
            }
    }

}