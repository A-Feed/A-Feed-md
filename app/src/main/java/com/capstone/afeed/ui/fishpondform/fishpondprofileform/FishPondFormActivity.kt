package com.capstone.afeed.ui.fishpondform.fishpondprofileform

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.databinding.ActivityFishPondFormBinding
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModel
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModelFactory
import com.capstone.afeed.ui.fishpondform.afeedingform.AFeedingFormActivity

class FishPondFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFishPondFormBinding
    private val fishPondFormViewModel: FishPondFormViewModel by viewModels {
        FishPondFormViewModelFactory.getInstance(
            this@FishPondFormActivity
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
        setupView()
    }

    private fun setupView() {
        var fishpondName = ""
        var fishpondDescription = ""
        with(binding) {
            includeProgressFormIndicator.apply {
                textIndicatorView1.setTextColor(getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView1.setImageResource(R.color.md_theme_primary)
            }
            inputTextFishpondName.addTextChangedListener {
                fishpondName = it.toString()
            }
            inputTextFishpondDescription.addTextChangedListener {
                fishpondDescription = it.toString()
            }
            fabNext.setOnClickListener {
                sendInputdataToViewmodel(
                    FishpondIotRequest.Fishpond(
                        fishpondDescription,
                        fishpondName
                    )
                )
                fishPondFormViewModel.fishpondData.observe(this@FishPondFormActivity) {
                    val intent = Intent(this@FishPondFormActivity, AFeedingFormActivity::class.java)
                    intent.putExtra(
                        FishPondFormViewModel.FISHPONDDATA_EXTRAS,
                        it
                    )
                    startActivity(intent)
                }
            }
        }
    }

    private fun sendInputdataToViewmodel(fishpond: FishpondIotRequest.Fishpond) {
        fishPondFormViewModel.saveFishpondProfile(fishpond)
    }


}