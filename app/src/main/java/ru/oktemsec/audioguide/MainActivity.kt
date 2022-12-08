package ru.oktemsec.audioguide

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init widgets
        val seekBar: SeekBar = findViewById(R.id.seek_bar)
        val playerDuration: TextView = findViewById(R.id.player_duration)
        val playerPosition: TextView = findViewById(R.id.player_position)
        val btPlay: ImageView = findViewById(R.id.bt_play)
        val btPause: ImageView = findViewById(R.id.bt_pause)
        val btFf: ImageView = findViewById(R.id.bt_ff)
        val btRew: ImageView = findViewById(R.id.bt_rew)

        // Media player
        //https://www.narakeet.com/app/text-to-audio/
        mediaPlayer = MediaPlayer.create(this, R.raw.welcome)
        mediaPlayer.setVolume(0.8f, 0.8f)

        //Make sure you update Seekbar on UI thread
        val runnable: Runnable = object: Runnable {
            override fun run() {
                seekBar.progress = mediaPlayer.currentPosition
                handler.postDelayed(this, 500)
            }
        }

        btPlay.setOnClickListener {
            playSound(playerDuration, btPlay, btPause, seekBar, runnable)
        }

        btPause.setOnClickListener {
            btPause.visibility = View.GONE
            btPlay.visibility = View.VISIBLE
            mediaPlayer.pause()
            handler.removeCallbacks(runnable)
        }

        btFf.setOnClickListener {
            var currentPosition = mediaPlayer.currentPosition.toLong()
            val duration = mediaPlayer.duration.toLong()

            if (mediaPlayer.isPlaying && duration != currentPosition) {
                currentPosition = currentPosition + 5000
                playerPosition.text = convertFormat(currentPosition)
                mediaPlayer.seekTo(currentPosition.toInt())
            }
        }

        btRew.setOnClickListener {
            var currentPosition = mediaPlayer.currentPosition.toLong()
            if (mediaPlayer.isPlaying && currentPosition > 5000) {
                currentPosition = currentPosition - 5000
                playerPosition.text = convertFormat(currentPosition)
                mediaPlayer.seekTo(currentPosition.toInt())
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
                playerPosition.text = convertFormat(mediaPlayer.currentPosition.toLong())
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {    }
        })

        mediaPlayer.setOnCompletionListener {
            btPause.visibility = View.GONE
            btPlay.visibility = View.VISIBLE
            mediaPlayer.seekTo(0)
        }

        // Play on start app
        playSound(playerDuration, btPlay, btPause, seekBar, runnable)
        // after welcome play Main menu sound
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.reset()
            mediaPlayer = MediaPlayer.create(this, R.raw.main_menu)
            playSound(playerDuration, btPlay, btPause, seekBar, runnable)
        }
    }

    private fun convertFormat(duration: Long): String {
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(duration),
            TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        )
    }

    private fun playSound(playerDuration: TextView, btnPlay: ImageView, btnPause:ImageView, seekBar: SeekBar, runnable: Runnable) {
        // Duration
        val duration = mediaPlayer.duration.toLong()
        val sDuration: String = convertFormat(duration)
        playerDuration.text = sDuration
        btnPlay.visibility = View.GONE
        btnPause.visibility = View.VISIBLE
        mediaPlayer.start()
        seekBar.max = mediaPlayer.duration
        handler.postDelayed(runnable, 0)
    }
}