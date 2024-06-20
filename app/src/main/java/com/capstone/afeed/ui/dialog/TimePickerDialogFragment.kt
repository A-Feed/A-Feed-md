package com.capstone.afeed.ui.dialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment

class TimePickerDialogFragment : DialogFragment() ,TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calender = Calendar.getInstance()
        val hour = calender.get(Calendar.HOUR_OF_DAY)
        val minutue = calender.get(Calendar.MINUTE)
        return TimePickerDialog(activity,this,hour,minutue,true)
    }

    data class Timenya(
            val hour: Int,
            val minute : Int
            )
    override fun onTimeSet(timePicker: TimePicker, hour: Int, minute: Int) {
        Log.i("datas",Timenya(hour,minute).toString())
    }
}