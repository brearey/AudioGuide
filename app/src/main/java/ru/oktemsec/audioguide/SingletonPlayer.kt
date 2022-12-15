package ru.oktemsec.audioguide

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import java.util.concurrent.TimeUnit

class SingletonPlayer(context: Context, songsListIn: List<Uri>, playButton: ImageView, pauseButton: ImageView) {
    private var currentIndex = 0
    val instance: MediaPlayer
    val songsList = songsListIn
    val btPlay = playButton
    val btPause = pauseButton
    var canPlay: Boolean = false
    val context = context

    init {
        instance = MediaPlayer()
        instance.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()
        )
        instance.setDataSource(context, songsList[currentIndex])
        instance.prepareAsync()
        instance.setOnPreparedListener {
            canPlay = true
        }

        Log.d("brearey", "Init: instance: $instance")
    }

    fun play() {
        if (canPlay) instance.start()
        updateUI()
        Log.d("brearey", "Can play: $canPlay")
        Log.d("brearey", "call play")
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
        } else if (currentIndex >= songsList.size) {
            currentIndex = 0
        }
        Log.d("brearey", "call next song")
        instance.stop()
        instance.reset()
        instance.setDataSource(context, songsList[currentIndex])
        instance.prepareAsync()
        instance.setOnPreparedListener {
            it.start()
            updateUI()
        }
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
    }

    private fun convertFormat(duration: Long): String {
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(duration),
            TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        )
    }
}