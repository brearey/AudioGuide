package ru.oktemsec.audioguide.questgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.oktemsec.audioguide.R

class QuestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)

        // Toolbar title and back button
        title = TITLE
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        const val TITLE = "Квест игра"
    }
}