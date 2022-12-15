package ru.oktemsec.audioguide

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } catch (e: Exception) {
            Log.e("brearey", e.message.toString())
        }
    }
}