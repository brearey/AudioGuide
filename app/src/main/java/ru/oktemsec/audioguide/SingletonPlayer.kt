package ru.oktemsec.audioguide

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import java.util.concurrent.TimeUnit

class SingletonPlayer(context: Context, songsListIn: List<Sound>, playButton: ImageView, pauseButton: ImageView, seekBar: SeekBar, playerPosition: TextView, playerTitle: TextView) {
    private var currentIndex = 0
    val instance: MediaPlayer
    val songsList = songsListIn
    val btPlay = playButton
    val btPause = pauseButton
    val playerTitle = playerTitle
    val playerPosition = playerPosition
    val seekBar = seekBar
    private val handler = Handler()
    var canPlay: Boolean = false
    val context = context
    lateinit var runnable: Runnable

    init {
        instance = MediaPlayer()
        instance.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()
        )
        instance.setDataSource(context, songsList[currentIndex].soundUri)
        playerTitle.text = songsList[currentIndex].soundName
        instance.prepareAsync()
        instance.setOnPreparedListener {
            canPlay = true
        }

        startRunnable()

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                playerPosition.text = convertFormat(instance.currentPosition.toLong())
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {    }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {    }
        })
        seekBar.isEnabled = false

        Log.d("brearey", "Init: instance: $instance")
    }

    fun play() {
        if (canPlay) instance.start()
        updateUI()
        Log.d("brearey", "Can play: $canPlay")
        Log.d("brearey", "call play")

        handler.postDelayed(runnable, 0)

        instance.setOnCompletionListener {
            updateUI()
        }
    }

    fun pause() {
        if (instance.isPlaying) {
            instance.pause()
        }
        updateUI()

        Log.d("brearey", "call pause")
    }

    fun nextSong() {
        if (currentIndex <  songsList.size - 1) {
            currentIndex++
        } else if (currentIndex <= songsList.size - 1) {
            currentIndex = 0
        }
        Log.d("brearey", "call next song")
        instance.stop()
        instance.reset()
        instance.setDataSource(context, songsList[currentIndex].soundUri)
        instance.prepareAsync()
        instance.setOnPreparedListener {
            it.start()
            updateUI()
        }

        instance.setOnCompletionListener {
            updateUI()
        }
        updateUI()
    }

    fun prevSong() {
        if (currentIndex > 0) {
            currentIndex--
        } else if (currentIndex <= 0) {
            currentIndex = songsList.size - 1
        }
        Log.d("brearey", "call next song")
        instance.stop()
        instance.reset()
        instance.setDataSource(context, songsList[currentIndex].soundUri)
        instance.prepareAsync()
        instance.setOnPreparedListener {
            it.start()
            updateUI()
        }

        instance.setOnCompletionListener {
            updateUI()
        }
        updateUI()
    }

    fun updateUI() {
        if (instance.isPlaying) {
            btPlay.visibility = View.GONE
            btPause.visibility = View.VISIBLE
        }
        else {
            btPlay.visibility = View.VISIBLE
            btPause.visibility = View.GONE
        }
        Log.d("brearey", "UI updated")
        Log.d("brearey", "Current idnex: $currentIndex")
        playerTitle.text = songsList[currentIndex].soundName
        playerPosition.text = convertFormat(instance.currentPosition.toLong())
        seekBar.progress = instance.currentPosition
        seekBar.max = instance.duration
        Log.d("brearey", "currentPosition: ${instance.currentPosition}")
        Log.d("brearey", "duration: ${instance.duration}")
        if (instance.currentPosition >= instance.duration) {
            seekBar.progress = 0
            playerPosition.text = convertFormat(0)
        }
    }

    fun destroy() {
        instance.stop()
        instance.release()
        handler.removeCallbacks(runnable)
    }

    private fun startRunnable() {
        //Make sure you update Seekbar on UI thread
        runnable = object: Runnable {
            override fun run() {
                if (instance.isPlaying) seekBar.progress = instance.currentPosition
                handler.postDelayed(this, 500)
            }
        }
    }

    fun convertFormat(duration: Long): String {
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(duration),
            TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        )
    }
}