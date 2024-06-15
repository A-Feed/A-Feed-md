package com.capstone.afeed.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.capstone.afeed.ui.introduction.IntroductionActivity


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000L)
        installSplashScreen()
        Intent(this, IntroductionActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}