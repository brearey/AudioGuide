// saved on desktop/temp.kt

package ru.oktemsec.audioguide

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Player widgets vars
    private lateinit var seekBar: SeekBar
    private lateinit var playerTitle: TextView
    private lateinit var playerPosition: TextView
    private lateinit var btPlay: ImageView
    private lateinit var btPause: ImageView
    private lateinit var btFf: ImageView
    private lateinit var btRew: ImageView

    private val RESOURCE_PREFIX = "android.resource://ru.oktemsec.audioguide/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init player widgets
        seekBar = findViewById(R.id.seek_bar)
        playerTitle = findViewById(R.id.player_title)
        playerPosition = findViewById(R.id.player_position)
        btPlay = findViewById(R.id.bt_play)
        btPause = findViewById(R.id.bt_pause)
        btFf = findViewById(R.id.bt_ff)
        btRew = findViewById(R.id.bt_rew)

        // List of songs
        val songsList: List<Sound> = listOf(
            Sound("Добро пожаловать", R.raw.welcome),
            Sound("Главное меню", R.raw.main_menu),
        )

        // Test singleton Player
        val player = SingletonPlayer(this, songsList, playButton = btPlay, pauseButton = btPause, seekBar = seekBar, playerPosition = playerPosition, playerTitle = playerTitle)


        btPlay.setOnClickListener {
            player.play()
        }

        btPause.setOnClickListener {
            player.pause()
        }

        btFf.setOnClickListener {
            player.nextSong()
        }

        btRew.setOnClickListener {

        }
    }
}

// Начать оптимизацию плеера по инструкциям:
// https://rrtutors.com/tutorials/how-to-use-media-player-to-play-list-of-audio-files-in-android-studio-using-kotlin
// https://www.javatpoint.com/kotlin-android-media-player