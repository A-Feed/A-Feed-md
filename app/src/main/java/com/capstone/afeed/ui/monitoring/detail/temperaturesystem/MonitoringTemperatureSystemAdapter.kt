package com.capstone.afeed.ui.monitoring.detail.temperaturesystem

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.response.GetListTemperatureSystemFromFishPondIDResponse
import com.capstone.afeed.databinding.ItemCardIotSystemInfoBinding

class MonitoringTemperatureSystemAdapter : ListAdapter<GetListTemperatureSystemFromFishPondIDResponse.Data, MonitoringTemperatureSystemAdapter.ViewHolder>
    (DIFF_CALLBACK) {
    inner class ViewHolder(val binding: ItemCardIotSystemInfoBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetListTemperatureSystemFromFishPondIDResponse.Data, position: Int) {
            with(binding){
                textViewTitle.setText(R.string.temperature_system)
                textViewStatus.text = item.status
                textViewIdSystem.text = item.idTopicMqtt
                textViewTitleScore.setText(context.getString(R.string.temperature_score))
                textViewScore.text = item.temperatureScore
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
            object : DiffUtil.ItemCallback<GetListTemperatureSystemFromFishPondIDResponse.Data>() {
                override fun areItemsTheSame(
                    oldItem: GetListTemperatureSystemFromFishPondIDResponse.Data,
                    newItem: GetListTemperatureSystemFromFishPondIDResponse.Data
                ): Boolean {
                    return oldItem.idTempSystem == newItem.idTempSystem
                }

                override fun areContentsTheSame(
                    oldItem: GetListTemperatureSystemFromFishPondIDResponse.Data,
                    newItem: GetListTemperatureSystemFromFishPondIDResponse.Data
                ): Boolean {
                    return oldItem.idTempSystem == newItem.idTempSystem
                }
            }
    }
}
