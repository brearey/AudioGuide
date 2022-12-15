// saved on desktop/temp.kt

package ru.oktemsec.audioguide

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    // Sound vars
    private lateinit var welcomeSound: MediaPlayer
    private lateinit var mainMenuSound: MediaPlayer

    // Player widgets vars
    private lateinit var seekBar: SeekBar
    private lateinit var playerDuration: TextView
    private lateinit var playerPosition: TextView
    private lateinit var btPlay: ImageView
    private lateinit var btPause: ImageView
    private lateinit var btFf: ImageView
    private lateinit var btRew: ImageView
    private val handler = Handler()

    private val RESOURCE_PREFIX = "android.resource://ru.oktemsec.audioguide/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init player widgets
        seekBar = findViewById(R.id.seek_bar)
        playerDuration = findViewById(R.id.player_duration)
        playerPosition = findViewById(R.id.player_position)
        btPlay = findViewById(R.id.bt_play)
        btPause = findViewById(R.id.bt_pause)
        btFf = findViewById(R.id.bt_ff)
        btRew = findViewById(R.id.bt_rew)

        // List of songs
        val songsList: List<Uri> = listOf(
            Uri.parse(RESOURCE_PREFIX + R.raw.welcome),
            Uri.parse(RESOURCE_PREFIX + R.raw.main_menu)
        )

        // Test singleton Player
        //val player = SingletonPlayer(this, songsList, playButton = btPlay, pauseButton = btPause)


        btPlay.setOnClickListener {
            //player.play()
        }

        btPause.setOnClickListener {
            //player.pause()
        }

        btFf.setOnClickListener {
            //player.nextSong()
        }

        btRew.setOnClickListener {

        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {    }
        })
    }
}

// Начать оптимизацию плеера по инструкциям:
// https://rrtutors.com/tutorials/how-to-use-media-player-to-play-list-of-audio-files-in-android-studio-using-kotlin
// https://www.javatpoint.com/kotlin-android-media-player