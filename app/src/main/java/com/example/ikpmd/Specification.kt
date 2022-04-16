package com.example.ikpmd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Specification : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sepcification)

        val name = findViewById<TextView>(R.id.s_name)
        val image = findViewById<ImageView>(R.id.s_image)
        val inComingText = intent.getStringExtra(Constants.name_waza)
        val inComingImage = intent.getIntExtra(Constants.pic_waza, 0)

        name.text = inComingText
        image.setImageResource(inComingImage)

    }
}