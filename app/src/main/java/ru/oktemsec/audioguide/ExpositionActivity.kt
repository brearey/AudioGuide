package ru.oktemsec.audioguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView

class ExpositionActivity : AppCompatActivity() {

    // Player widgets vars
    private lateinit var seekBar: SeekBar
    private lateinit var playerTitle: TextView
    private lateinit var playerPosition: TextView
    private lateinit var btPlay: ImageView
    private lateinit var btPause: ImageView
    private lateinit var btNext: ImageView
    private lateinit var btPrev: ImageView

    // Player object
    lateinit var player: SingletonPlayer

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
        val soundsList:List<Sound> = expositionsList[intent.getIntExtra("index", expositionsList.size - 1)].soundsList

        expositionImageView.setImageResource(expositionImageResource)
        expositionDescTextView.text = expositionDescription

        // Toolbar title and back button
        setTitle(expositionNameResource)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Init player widgets
        seekBar = findViewById(R.id.seek_bar)
        playerTitle = findViewById(R.id.player_title)
        playerPosition = findViewById(R.id.player_position)
        btPlay = findViewById(R.id.bt_play)
        btPause = findViewById(R.id.bt_pause)
        btNext = findViewById(R.id.bt_next)
        btPrev = findViewById(R.id.bt_prev)

        // Test singleton Player
        player = SingletonPlayer(this, songsListIn = soundsList, playButton = btPlay, pauseButton = btPause, seekBar = seekBar, playerPosition = playerPosition, playerTitle = playerTitle)


        btPlay.setOnClickListener {
            player.play()
        }

        btPause.setOnClickListener {
            player.pause()
        }

        btNext.setOnClickListener {
            player.nextSong()
        }

        btPrev.setOnClickListener {
            player.prevSong()
        }
        // Player end
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.destroy()
    }

    // Back button on toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}