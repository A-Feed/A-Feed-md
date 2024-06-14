package com.capstone.afeed.ui.fishpondform.afeedingform

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.databinding.ItemTextInputLayoutWithLabelBinding

class AFeedingSystemAdapter :
    ListAdapter<FishpondIotRequest.AFeedingSystem, AFeedingSystemAdapter.ViewHolder>(DIFF_CALLBACK) {

    interface AddNewListener {
        fun insertItemListener(dataInput: FishpondIotRequest.AFeedingSystem, id: Int)
        fun deleteItemListener(id: Int)
    }

    var addNewListener: AddNewListener? = null

    inner class ViewHolder(val binding: ItemTextInputLayoutWithLabelBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FishpondIotRequest.AFeedingSystem, position: Int) {
            with(binding) {
                checkBoxItem.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        insertDataListener(item.id, inputText.text.toString())
                    }
                }
                if (item.idTopicMqtt.isBlank()){
                    inputText.setText("")
                }else{
                    inputText.setText(item.idTopicMqtt)
                }
                inputText.addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        checkBoxItem.isChecked = false
                    }

                } )
                textViewLabel.setText(context.getString(R.string.id_system))
                textInputlayout.setHint(context.getString(R.string.id_system))
                btnDelete.setOnClickListener {
                    addNewListener?.deleteItemListener(item.id)
                }
            }
        }

    }

    private fun insertDataListener(id: Int, idTopicMqtt: String) {
        val dataInput = FishpondIotRequest.AFeedingSystem(id, idTopicMqtt)
        addNewListener?.insertItemListener(dataInput, id)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AFeedingSystemAdapter.ViewHolder {
        val binding = ItemTextInputLayoutWithLabelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: AFeedingSystemAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FishpondIotRequest.AFeedingSystem>() {
            override fun areItemsTheSame(
                oldItem: FishpondIotRequest.AFeedingSystem,
                newItem: FishpondIotRequest.AFeedingSystem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FishpondIotRequest.AFeedingSystem,
                newItem: FishpondIotRequest.AFeedingSystem
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}