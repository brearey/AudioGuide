package ru.oktemsec.audioguide

import android.net.Uri

data class Sound(val soundName: String, val resource: Int) {
    private val RESOURCE_PREFIX = "android.resource://ru.oktemsec.audioguide/"
    val name = soundName
    val soundUri = Uri.parse(RESOURCE_PREFIX + resource)
}
