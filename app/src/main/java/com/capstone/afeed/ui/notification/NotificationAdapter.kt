package com.capstone.afeed.ui.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.afeed.data.remote.response.NotificationResponse
import com.capstone.afeed.databinding.ItemNotificationBinding

class NotificationAdapter : ListAdapter<NotificationResponse,NotificationAdapter.ViewHolder>(DIFF_CALLBACK) {
    inner class ViewHolder(val binding :ItemNotificationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NotificationResponse) {
            with(binding){
                textViewTitleNotifcation.text = item.messageHeader
                textViewDescriptionNotifcation.text = item.messegeBody
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val DIFF_CALLBACK = object :DiffUtil.ItemCallback<NotificationResponse>(){
            override fun areItemsTheSame(
                oldItem: NotificationResponse,
                newItem: NotificationResponse
            ): Boolean {
                return false
            }

            override fun areContentsTheSame(
                oldItem: NotificationResponse,
                newItem: NotificationResponse
            ): Boolean {
                return false
            }

        }
    }
}