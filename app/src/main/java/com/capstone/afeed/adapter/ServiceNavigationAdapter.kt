package com.capstone.afeed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.afeed.data.local.model.NavigationWithIcon
import com.capstone.afeed.databinding.ListColumnNavigationCardBinding
import com.capstone.afeed.utils.loadImage

class ServiceNavigationAdapter : ListAdapter<NavigationWithIcon, ServiceNavigationAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    interface AddNewListener{
        fun onClickListener(item:NavigationWithIcon,binding: ListColumnNavigationCardBinding)
    }

    var addNewListener : AddNewListener? = null
    inner class ViewHolder(val binding: ListColumnNavigationCardBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NavigationWithIcon) {
            with(binding){
                columnNavigationTextViewTitle.text = item.title
                if (item.lefIcon == null){
                    cardViewLeftIconWrapper.visibility = View.GONE
                    columnNavigationLeftIcon.visibility = View.GONE
                }
                if (item.rightIcon == null){
                    columnNavigationRightIcon.visibility = View.GONE
                }
                item.lefIcon?.let { columnNavigationLeftIcon.loadImage(it) }
                item.rightIcon?.let { columnNavigationRightIcon.loadImage(it) }
                columnNavigationTextViewTitle.text = item.title
                columnNavigationTextViewDescription.text = item.description
                columnNavigationCard.setOnClickListener {
                    addNewListener?.onClickListener(item,binding)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListColumnNavigationCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NavigationWithIcon>() {
            override fun areItemsTheSame(
                oldItem: NavigationWithIcon,
                newItem: NavigationWithIcon
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NavigationWithIcon,
                newItem: NavigationWithIcon
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}