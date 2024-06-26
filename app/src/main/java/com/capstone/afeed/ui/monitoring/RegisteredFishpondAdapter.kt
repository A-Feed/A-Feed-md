package com.capstone.afeed.ui.monitoring

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.response.GetListFishPondResponse
import com.capstone.afeed.databinding.ListColumnNavigationCardBinding

class RegisteredFishpondAdapter :
    ListAdapter<GetListFishPondResponse.Pond, RegisteredFishpondAdapter.ViewHolder>(DIFF_CALLBACK) {
    interface AddNewListener {
        fun onClickItem(item: GetListFishPondResponse.Pond)

    }

    var addNewListener: RegisteredFishpondAdapter.AddNewListener? = null

    inner class ViewHolder(val binding: ListColumnNavigationCardBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetListFishPondResponse.Pond) {
            with(binding) {
                columnNavigationCard.setOnClickListener {
                    addNewListener?.onClickItem(item)
                }
                cardViewLeftIconWrapper.visibility = View.GONE
                columnNavigationLeftIcon.setImageDrawable(context.getDrawable(R.drawable.ic_right_arrow))
                columnNavigationRightIcon.layoutParams.apply {
                    width = 25
                    height = 25
                }
                columnNavigationTextViewTitle.text = item.fishpondName
                columnNavigationTextViewDescription.text = item.fishpondDescription
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListColumnNavigationCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GetListFishPondResponse.Pond>() {
            override fun areItemsTheSame(
                oldItem: GetListFishPondResponse.Pond,
                newItem: GetListFishPondResponse.Pond
            ): Boolean {
                return oldItem.fishpondId == newItem.fishpondId
            }

            override fun areContentsTheSame(
                oldItem: GetListFishPondResponse.Pond,
                newItem: GetListFishPondResponse.Pond
            ): Boolean {
                return oldItem.fishpondId == newItem.fishpondId
            }

        }
    }
}