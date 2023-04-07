package ru.oktemsec.audioguide.exhibit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ru.oktemsec.audioguide.R
import ru.oktemsec.audioguide.Repository

class ExhibitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exhibit)

        // get exhibit index
        val repository = Repository()
        val exhibit:Exhibit = repository.exhibitLists[intent.getIntExtra("index", 0)]

        // widgets
        val exhibitNameTV = findViewById<TextView>(R.id.exhibit_name)
        val exhibitDescTV = findViewById<TextView>(R.id.exhibit_description)
        val exhibitImageView = findViewById<ImageView>(R.id.exhibit_image)

        exhibitNameTV.text = exhibit.name
        exhibitDescTV.text = exhibit.description

        //set image with Picasso
        Picasso.get().load(exhibit.imageUrl).into(exhibitImageView)
    }
}