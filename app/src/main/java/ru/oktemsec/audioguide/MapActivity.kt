package ru.oktemsec.audioguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Toolbar title and back button
        setTitle("Карта музея")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Back button on toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}