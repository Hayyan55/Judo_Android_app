package com.example.ikpmd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class Specification : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sepcification)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_add_place)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val name = findViewById<TextView>(R.id.s_name)
        val description = findViewById<TextView>(R.id.s_description)
        val inComingName = intent.getStringExtra(Constants.name_waza)
        val inComingDescription = intent.getStringExtra(Constants.des_waza)

        name.text = inComingName
        description.text = inComingDescription
    }
}