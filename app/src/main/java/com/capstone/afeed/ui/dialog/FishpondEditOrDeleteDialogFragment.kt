package com.capstone.afeed.ui.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.capstone.afeed.R
import com.capstone.afeed.databinding.DialogSuccessFragmentBinding
import com.capstone.afeed.databinding.FragmentFishpondEditOrDeleteDialogBinding

class FishpondEditOrDeleteDialogFragment : DialogFragment() {

    private var _binding : FragmentFishpondEditOrDeleteDialogBinding? = null
    private val binding get() = _binding!!

    interface AddNewListener {
        fun onClickEdit()
        fun onClickDelete()
    }

    var addNewListener : AddNewListener? = null

    override fun onResume() {
        super.onResume()
        val getDisplayDimension = resources.displayMetrics
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        this.dialog?.window?.setLayout((getDisplayDimension.widthPixels * 9) /10, WindowManager.LayoutParams.WRAP_CONTENT)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         _binding = FragmentFishpondEditOrDeleteDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            buttonDelete.setOnClickListener {
                addNewListener?.onClickDelete()
            }
            buttonEdit.setOnClickListener {
                addNewListener?.onClickEdit()
            }
            textViewTitleCard.text = arguments?.getString(TITLE_CARD_EXTRAS)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        val TAG = "fishpondEditOrDeleteDialogFragment"
        val TITLE_CARD_EXTRAS = "title_card_extras"
    }
}