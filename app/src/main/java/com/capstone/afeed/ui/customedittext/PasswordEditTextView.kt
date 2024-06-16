package com.capstone.afeed.ui.customedittext

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.doAfterTextChanged
import com.capstone.afeed.R
import com.google.android.material.textfield.TextInputEditText

class PasswordEditTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TextInputEditText(context, attrs) {
    init {
        doAfterTextChanged { theText ->
            if (theText.toString().length < 8) {
                error = context.getString(R.string.password_length_validation)
            }
        }
    }
}