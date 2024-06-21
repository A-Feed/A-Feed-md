package com.capstone.afeed.ui.fishpondform

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat.getExtras
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import com.capstone.afeed.R
import com.capstone.afeed.databinding.ActivityFishPondFormBinding

class FishPondFormActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFishPondFormBinding
    private val fishPondFormViewModel: FishPondFormViewModel by viewModels {
        FishPondFormViewModelFactory.getInstance(
            this
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFishPondFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getExtras()
    }

    private fun getExtras() {
        val formMode = intent.getStringExtra(FORM_MODE_EXTRAS)
        if (formMode != null) {
            fishPondFormViewModel.setFormMode(formMode)
        }
        when(formMode){
            FORM_MODE_EDIT -> {
                with(binding){
                    fragmentContainerViewFishPondFormEditMode.visibility = View.VISIBLE
                    fragmentContainerViewFishPondForm.visibility = View.GONE
                }
            }
            FORM_MODE_ADD -> {
                with(binding){
                    fragmentContainerViewFishPondFormEditMode.visibility = View.GONE
                    fragmentContainerViewFishPondForm.visibility = View.VISIBLE
                }
            }
        }

    }

    companion object{
        const val FORM_MODE_EXTRAS = "form_mode"
        const val FORM_MODE_EDIT = "form_mode_edit"
        const val FORM_MODE_ADD = "form_mode_add"
    }


}