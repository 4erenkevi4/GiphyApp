package com.example.giphyapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.giphyapp.R
import com.example.giphyapp.constans.Constants.Companion.GIF_TITLE
import com.example.giphyapp.constans.Constants.Companion.GIF_URL

class ShowGifActivity : AppCompatActivity() {
    private var showGif: String? = null
    private var showTitle: String? = null
    private var gifName: TextView? = null
    private lateinit var gifImage: ImageView
    private lateinit var shareButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_gif)
        gifImage = findViewById(R.id.gif_imageview)
        gifName = findViewById(R.id.title_text_view)
        shareButton = findViewById(R.id.share_button)
        val intent = intent.extras
        if (intent != null) {
            showGif = intent.getString(GIF_URL)
            showTitle = intent.getString(GIF_TITLE)
        }
        showGif?.let {
            Glide
                .with(this)
                .load(it)
                .into(gifImage)
        }
        showTitle?.let {
            gifName?.text = it
        }
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, showGif.toString())
        shareIntent.type = "text/plain"
        shareButton.setOnClickListener {
            startActivity(shareIntent)
        }
    }
}


