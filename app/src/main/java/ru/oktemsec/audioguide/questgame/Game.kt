package ru.oktemsec.audioguide.questgame

import androidx.appcompat.app.AppCompatActivity
import ru.oktemsec.audioguide.R
import ru.oktemsec.audioguide.Repository
import ru.oktemsec.audioguide.exhibit.Exhibit
import kotlin.properties.Delegates

class Game(activity: AppCompatActivity) {
    lateinit var instance:Game
    var repository: Repository
    lateinit var currentExhibit: Exhibit
    var rules: String
    var scores by Delegates.notNull<Int>()

    init {
        scores = 0
        rules = activity.getString(R.string.quest_game_rules)
        repository = Repository()
        getNextExhibit()
    }

    fun getNextExhibit() {
        currentExhibit = repository.exhibitLists.random()
    }

    fun checkAnswer(scannedQR: String): String {
        val userAnswer = findExhibit(scannedQR)
        if (userAnswer == null) {
            scores++
            return "Your answer is wrong"
        } else if (userAnswer.equals(currentExhibit)) {
            return "You are right, well done"
        }
        return "Something went wrong"
    }

    private fun findExhibit(scannedQR: String): Exhibit? {
        return repository.exhibitLists.find {
            it.url == scannedQR
        }
    }
}