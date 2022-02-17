package com.chiper.test.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chiper.test.MainActivity
import com.chiper.test.R
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(object : TimerTask(){
            override fun run() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }, SPLASH_DELAY)
    }

    companion object {
        private const val SPLASH_DELAY = 3_000L
    }
}