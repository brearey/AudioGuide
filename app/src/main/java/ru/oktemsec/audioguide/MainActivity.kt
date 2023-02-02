package ru.oktemsec.audioguide

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import ru.oktemsec.audioguide.qrscanner.ScannerHandler


class MainActivity : AppCompatActivity() {

    // Player widgets vars
    private lateinit var seekBar: SeekBar
    private lateinit var playerTitle: TextView
    private lateinit var playerPosition: TextView
    private lateinit var btPlay: ImageView
    private lateinit var btPause: ImageView
    private lateinit var btNext: ImageView
    private lateinit var btPrev: ImageView

    //qr scanner
    private lateinit var scanButton: ImageButton

    // Player object
    lateinit var player: SingletonPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init player widgets
        seekBar = findViewById(R.id.seek_bar)
        playerTitle = findViewById(R.id.player_title)
        playerPosition = findViewById(R.id.player_position)
        btPlay = findViewById(R.id.bt_play)
        btPause = findViewById(R.id.bt_pause)
        btNext = findViewById(R.id.bt_next)
        btPrev = findViewById(R.id.bt_prev)
        // qr scanner
        scanButton = findViewById(R.id.scan_button)

        // List of songs
        val songsListMainMenu: List<Sound> = listOf(
            Sound("Добро пожаловать", R.raw.welcome),
            Sound("Главное меню", R.raw.main_menu),
        )

        // Test singleton Player
        player = SingletonPlayer(
            this,
            songsListMainMenu,
            playButton = btPlay,
            pauseButton = btPause,
            seekBar = seekBar,
            playerPosition = playerPosition,
            playerTitle = playerTitle
        )


        btPlay.setOnClickListener {
            player.play()
        }

        btPause.setOnClickListener {
            player.pause()
        }

        btNext.setOnClickListener {
            player.nextSong()
        }

        btPrev.setOnClickListener {
            player.prevSong()
        }
        // Player end

        // Buttons of main menu
        val button0: CardView = findViewById(R.id.zal_istoria_obraz)
        val button1: CardView = findViewById(R.id.zal_alex_narod)
        val button2: CardView = findViewById(R.id.zal_class_soviet)
        val button3: CardView = findViewById(R.id.zal_school_in_soviet)
        val button4: CardView = findViewById(R.id.zal_new_history_school)
        val button5: CardView = findViewById(R.id.zal_kraeved_ugolok)

        // Set listener all buttons and goto exposition activity with extras
        button0.setOnClickListener {
            val intent = Intent(this, ExpositionActivity::class.java)
            intent.putExtra("index", 0)
            startActivity(intent)
        }
        button1.setOnClickListener {
            val intent = Intent(this, ExpositionActivity::class.java)
            intent.putExtra("index", 1)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(this, ExpositionActivity::class.java)
            intent.putExtra("index", 2)
            startActivity(intent)
        }
        button3.setOnClickListener {
            val intent = Intent(this, ExpositionActivity::class.java)
            intent.putExtra("index", 3)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent = Intent(this, ExpositionActivity::class.java)
            intent.putExtra("index", 4)
            startActivity(intent)
        }
        button5.setOnClickListener {
            val intent = Intent(this, ExpositionActivity::class.java)
            intent.putExtra("index", 5)
            startActivity(intent)
        }

        // maps widgets
        val map1: ImageView = findViewById(R.id.map1)
        val map2: ImageView = findViewById(R.id.map2)
        val map3: ImageView = findViewById(R.id.map3)
        val map4: ImageView = findViewById(R.id.map4)
        val map5: ImageView = findViewById(R.id.map5)
        val map6: ImageView = findViewById(R.id.map6)

        // maps list
        val mapsList: List<ImageView> = listOf(
            map1, map2, map3, map4, map5, map6
        )

        // maps click listener
        mapsList.forEach {
            it.setOnClickListener {
                startActivity(Intent(this, MapActivity::class.java))
            }
        }
        // qr scanner
        val barcodeLauncher = registerForActivityResult( ScanContract()) { result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(this@MainActivity, "Сканирование отменено", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Отсканировано: " + result.contents,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        scanButton.setOnClickListener {
            ScannerHandler.scanCode(barcodeLauncher)
        }
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.destroy()
    }
}

// Начать оптимизацию плеера по инструкциям:
// https://rrtutors.com/tutorials/how-to-use-media-player-to-play-list-of-audio-files-in-android-studio-using-kotlin
// https://www.javatpoint.com/kotlin-android-media-player