package ru.oktemsec.audioguide.qrscanner

import androidx.activity.result.ActivityResultLauncher
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanOptions

class ScannerHandler {
    companion object {
        @JvmStatic
        fun scanCode(barLauncher: ActivityResultLauncher<ScanOptions>) {
            val options:ScanOptions = ScanOptions()
            options.setPrompt("Для включения фонаря нажмите ГРОМКОСТЬ +")
            options.setBeepEnabled(true)
            options.setOrientationLocked(true)
            options.captureActivity = CaptureAct::class.java
            barLauncher.launch(options)
        }
    }
}