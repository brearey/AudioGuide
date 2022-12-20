package ru.oktemsec.audioguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ExpositionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exposition)

        // Widgets vars
        val expositionImageView = findViewById<ImageView>(R.id.exposition_image)
        val expositionDescTextView = findViewById<TextView>(R.id.exposition_description)

        //Get exposition from intent extras
        val expositionsList = Repository().expositionsList
        val expositionNameResource = expositionsList[intent.getIntExtra("index", expositionsList.size - 1)].name
        val expositionImageResource = expositionsList[intent.getIntExtra("index", expositionsList.size - 1)].imageResource
        val expositionDescription = expositionsList[intent.getIntExtra("index", expositionsList.size - 1)].description

        expositionImageView.setImageResource(expositionImageResource)
        expositionDescTextView.text = expositionDescription

        // Toolbar title and back button
        setTitle(expositionNameResource)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Back button on toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}