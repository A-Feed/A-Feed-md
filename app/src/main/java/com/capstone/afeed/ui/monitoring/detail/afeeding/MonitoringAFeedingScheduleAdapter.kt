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
import com.capstone.afeed.databinding.ItemColumnAFeedingScheduleBinding

class MonitoringAFeedingScheduleAdapter : ListAdapter<GetListAFeedingFromFishPondIDResponse.Schedule, MonitoringAFeedingScheduleAdapter.ViewHolder>(DIFF_CALLBACK) {
    inner class ViewHolder(val binding: ItemColumnAFeedingScheduleBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetListAFeedingFromFishPondIDResponse.Schedule, position: Int) {
            with(binding) {
                textViewNoSequence.text = (position + 1).toString()
                textViewFoodAmount.setText(context.getString(R.string.kg,item.foodAmount))
                textViewTime.text = item.time
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemColumnAFeedingScheduleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<GetListAFeedingFromFishPondIDResponse.Schedule>() {
                override fun areItemsTheSame(
                    oldItem: GetListAFeedingFromFishPondIDResponse.Schedule,
                    newItem: GetListAFeedingFromFishPondIDResponse.Schedule
                ): Boolean {
                    return oldItem.idAFeedingSchedule == newItem.idAFeedingSchedule
                }

                override fun areContentsTheSame(
                    oldItem: GetListAFeedingFromFishPondIDResponse.Schedule,
                    newItem: GetListAFeedingFromFishPondIDResponse.Schedule
                ): Boolean {
                    return oldItem.idAFeedingSchedule == newItem.idAFeedingSchedule
                }

            }
    }
}