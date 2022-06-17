package com.example.storyApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Bundle
import android.os.Looper

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(applicationContext,Onboarding::class.java))
        },2000)
    }
}