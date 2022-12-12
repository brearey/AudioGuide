package ru.oktemsec.audioguide

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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

        // Media player
        //https://www.narakeet.com/app/text-to-audio/

        welcomeSound = MediaPlayer()
        welcomeSound.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()
        )
        welcomeSound.setDataSource(this, Uri.parse(RESOURCE_PREFIX + R.raw.welcome))
        mainMenuSound = MediaPlayer.create(this, R.raw.main_menu)
        //welcomeSound.setVolume(0.8f, 0.8f)

        //Make sure you update Seekbar on UI thread
        val runnable: Runnable = object: Runnable {
            override fun run() {
                if (welcomeSound.isPlaying) seekBar.progress = welcomeSound.currentPosition
                else seekBar.progress = mainMenuSound.currentPosition
                handler.postDelayed(this, 500)
            }
        }

        btPlay.setOnClickListener {
            if (welcomeSound.isPlaying) playSound(welcomeSound, playerDuration, btPlay, btPause, seekBar, runnable)
            else playSound(mainMenuSound, playerDuration, btPlay, btPause, seekBar, runnable)
        }

        btPause.setOnClickListener {
            btPause.visibility = View.GONE
            btPlay.visibility = View.VISIBLE
            if (welcomeSound.isPlaying) welcomeSound.pause()
            else mainMenuSound.pause()
            handler.removeCallbacks(runnable)
        }

        btFf.setOnClickListener {
            if (welcomeSound.isPlaying) {
                var currentPosition = welcomeSound.currentPosition.toLong()

                if (welcomeSound.isPlaying && welcomeSound.duration.toLong() != currentPosition) {
                    currentPosition = currentPosition + 5000
                    playerPosition.text = convertFormat(currentPosition)
                    welcomeSound.seekTo(currentPosition.toInt())
                }
            }
            else {
                var currentPosition = mainMenuSound.currentPosition.toLong()

                if (mainMenuSound.isPlaying && mainMenuSound.duration.toLong() != currentPosition) {
                    currentPosition = currentPosition + 5000
                    playerPosition.text = convertFormat(currentPosition)
                    mainMenuSound.seekTo(currentPosition.toInt())
                }
            }
        }

        btRew.setOnClickListener {
            if (welcomeSound.isPlaying) {
                var currentPosition = welcomeSound.currentPosition.toLong()
                if (welcomeSound.isPlaying && currentPosition > 5000) {
                    currentPosition = currentPosition - 5000
                    playerPosition.text = convertFormat(currentPosition)
                    welcomeSound.seekTo(currentPosition.toInt())
                }
            }
            else {
                var currentPosition = mainMenuSound.currentPosition.toLong()
                if (mainMenuSound.isPlaying && currentPosition > 5000) {
                    currentPosition = currentPosition - 5000
                    playerPosition.text = convertFormat(currentPosition)
                    mainMenuSound.seekTo(currentPosition.toInt())
                }
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (welcomeSound.isPlaying) {
                    if (fromUser) {
                        welcomeSound.seekTo(progress)
                    }
                    playerPosition.text = convertFormat(welcomeSound.currentPosition.toLong())
                }
                else {
                    if (fromUser) {
                        mainMenuSound.seekTo(progress)
                    }
                    playerPosition.text = convertFormat(mainMenuSound.currentPosition.toLong())
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {    }
        })

        // Play on start app
        playSound(welcomeSound, playerDuration, btPlay, btPause, seekBar, runnable)

        // after welcome play Main menu sound
        welcomeSound.setOnCompletionListener {
            btPause.visibility = View.GONE
            btPlay.visibility = View.VISIBLE
            welcomeSound.seekTo(0)
            playSound(mainMenuSound, playerDuration, btPlay, btPause, seekBar, runnable)
        }
        mainMenuSound.setOnCompletionListener {
            btPause.visibility = View.GONE
            btPlay.visibility = View.VISIBLE
            mainMenuSound.seekTo(0)
            seekBar.progress = 0
        }
    }

    private fun convertFormat(duration: Long): String {
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(duration),
            TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        )
    }

    private fun playSound(sound: MediaPlayer, playerDuration: TextView, btnPlay: ImageView, btnPause:ImageView, seekBar: SeekBar, runnable: Runnable) {
        // Duration
        val duration = sound.duration.toLong()
        val sDuration: String = convertFormat(duration)
        playerDuration.text = sDuration
        btnPlay.visibility = View.GONE
        btnPause.visibility = View.VISIBLE
        sound.prepareAsync()
        sound.setOnPreparedListener {
            it.start()
        }
        seekBar.max = sound.duration
        handler.postDelayed(runnable, 0)
    }
}