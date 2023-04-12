package ru.oktemsec.audioguide.questgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import org.w3c.dom.Text
import ru.oktemsec.audioguide.R
import ru.oktemsec.audioguide.exhibit.ExhibitActivity
import ru.oktemsec.audioguide.qrscanner.ScannerHandler

class QuestActivity : AppCompatActivity() {

    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)

        // Toolbar title and back button
        title = "Квест игра"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // widgets
        val exhibitQuestionTextView: TextView = findViewById(R.id.exhibit_question)
        val qrScannerButton: ImageButton = findViewById(R.id.open_qr_scanner)

        // Game
        game = Game(this)
        game.getNextExhibit()

        val barcodeLauncher = registerForActivityResult( ScanContract()) { result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(this, "Сканирование отменено", Toast.LENGTH_SHORT).show()
            } else {
                val resultToUser = game.checkAnswer(result.contents)
                Toast.makeText(this, "$resultToUser \n Ваши очки: ${game.scores}", Toast.LENGTH_LONG).show()
            }
        }

        qrScannerButton.setOnClickListener {
            ScannerHandler.scanCode(barcodeLauncher)
        }
    }
}