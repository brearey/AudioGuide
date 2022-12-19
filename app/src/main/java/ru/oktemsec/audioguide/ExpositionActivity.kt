package ru.oktemsec.audioguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ExpositionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exposition)

        //For test: display exposition name
        val textView: TextView = findViewById(R.id.textView)
        val expositionsList = Repository().expositionsList
        val expositionNameResource = expositionsList[intent.getIntExtra("index", expositionsList.size - 1)].name
        textView.text = resources.getText(expositionNameResource)

        // Toolbar
        setTitle(expositionNameResource)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}