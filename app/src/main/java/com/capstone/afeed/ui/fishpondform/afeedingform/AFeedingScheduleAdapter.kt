package com.capstone.afeed.ui.fishpondform.afeedingform

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.databinding.ItemColumnCardInputScheduleBinding
import com.capstone.afeed.ui.dialog.TimePickerDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class AFeedingScheduleAdapter(
    val fragment: Fragment
) :
    ListAdapter<FishpondIotRequest.AFeedingSchedule, AFeedingScheduleAdapter.ViewHolder>(
        DIFF_CALLBACK
    ) {

    interface AddNewListener {
        fun inputDataListener(dataInput: FishpondIotRequest.AFeedingSchedule, id: Int)
        fun deleteItemListener(id: Int)

    }

    var addNewListener: AddNewListener? = null

    inner class ViewHolder(val binding: ItemColumnCardInputScheduleBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        private val timePicker: MaterialTimePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(10)
                .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                .setTitleText(context.getString(R.string.select_feeding_time))
                .build()

        fun bind(item: FishpondIotRequest.AFeedingSchedule, itemCount: Int, position: Int) {
            with(binding) {
                checkBoxItemSchedule.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        inputDataListener(
                            item.id,
                            inputTextTime.text.toString(),
                            inputTextFoodAmount.text.toString(),
                        )
                    }
                }

                frameLayoutTimePickerTrigger.apply {
                    setOnClickListener {
                        timePicker.show(fragment.parentFragmentManager, "TIME PICKER ${id}")
                        timePicker.addOnPositiveButtonClickListener {
                            inputTextTime.setText("${timePicker.hour}:${timePicker.minute}")
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
                btnDeleteSchedule.setOnClickListener {
                    Log.i("datasadapter", item.id.toString())
                    addNewListener?.deleteItemListener(item.id)
                }
            }
        }

    }

    private fun inputDataListener(id: Int, timeInput: String, foodAmountInput: String) {
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