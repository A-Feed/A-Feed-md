package com.capstone.afeed.ui.fishpondform.afeedingform

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.databinding.ItemColumnCardInputScheduleBinding

class AFeedingScheduleAdapter :
    ListAdapter<FishpondIotRequest.AFeedingSchedule, AFeedingScheduleAdapter.ViewHolder>(
        DIFF_CALLBACK
    ) {
    interface AddNewListener {
        fun inputDataListener(dataInput: FishpondIotRequest.AFeedingSchedule, id: Int)
    }

    var addNewListener: AddNewListener? = null

    inner class ViewHolder(val binding: ItemColumnCardInputScheduleBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FishpondIotRequest.AFeedingSchedule, itemCount: Int, position: Int) {
            with(binding) {
                textViewSequenceNumber.text =
                    context.getString(R.string.no, (position + 1).toString())
                checkBoxItemSchedule.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        inputDataListener(
                            item.id,
                            inputTextTime.text.toString(),
                            inputTextFoodAmount.text.toString(),
                        )
                    }
                }
                inputTextTime.addTextChangedListener {
                    object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun afterTextChanged(p0: Editable?) {
                            checkBoxItemSchedule.isChecked = false
                        }

                    }
                }

                inputTextFoodAmount.addTextChangedListener {
                    object : TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun afterTextChanged(p0: Editable?) {
                            checkBoxItemSchedule.isChecked = false
                        }

                    }
                }

                inputTextTime.setText(item.time)
                inputTextFoodAmount.setText(item.foodAmount)
            }
        }

    }

    fun inputDataListener(id: Int, timeInput: String, foodAmountInput: String) {
        val dataInput = FishpondIotRequest.AFeedingSchedule(id, foodAmountInput, timeInput)
        addNewListener?.inputDataListener(dataInput, id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemColumnCardInputScheduleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemCount, position)
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FishpondIotRequest.AFeedingSchedule>() {
            override fun areItemsTheSame(
                oldItem: FishpondIotRequest.AFeedingSchedule,
                newItem: FishpondIotRequest.AFeedingSchedule
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FishpondIotRequest.AFeedingSchedule,
                newItem: FishpondIotRequest.AFeedingSchedule
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}