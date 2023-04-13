package ru.oktemsec.audioguide.questgame

import android.annotation.SuppressLint
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

        // Toolbar title
        title = "Квест игра"

        // widgets
        val exhibitQuestionTextView: TextView = findViewById(R.id.exhibit_question)
        val qrScannerButton: ImageButton = findViewById(R.id.open_qr_scanner)
        val scoresTextView: TextView = findViewById(R.id.scores)

        // Game
        game = Game(this)
        game.getNextExhibit()

        // Set question to UI
        updateUI(exhibitQuestionTextView, scoresTextView, game)

        val barcodeLauncher = registerForActivityResult( ScanContract()) { result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(this, "Сканирование отменено", Toast.LENGTH_SHORT).show()
            } else {
                val resultToUser = game.checkAnswer(result.contents)
                Toast.makeText(this, "$resultToUser \n Ваши очки: ${game.scores}", Toast.LENGTH_LONG).show()
                game.getNextExhibit()
                // Set question to UI
                updateUI(exhibitQuestionTextView, scoresTextView, game)
            }
        }

        qrScannerButton.setOnClickListener {
            ScannerHandler.scanCode(barcodeLauncher)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(questionTextView: TextView, scoresTextView: TextView, game: Game) {
        questionTextView.text = game.currentExhibit.mystery
        scoresTextView.text = "${getString(R.string.exhibits_count)} ${game.scores}"
    }
}