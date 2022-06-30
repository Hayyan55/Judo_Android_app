package com.example.ikpmd

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class AddNewWaza : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_waza)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_add_waza)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val saveButton = findViewById<Button>(R.id.btn_save)

        saveButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_save -> {
                val wazaName = findViewById<TextView>(R.id.et_name)
                val wazaDescription = findViewById<TextView>(R.id.et_description)

                when {
                    wazaName.text.isNullOrEmpty() -> {
                        Toast.makeText(this, "Please enter title", Toast.LENGTH_SHORT).show()
                    }
                    wazaDescription.text.isNullOrEmpty() -> {
                        Toast.makeText(this, "Please enter description", Toast.LENGTH_SHORT).show()
                    }
                    else -> {

                        // Assigning all the values to data model class.
                        val waza = Waza(
                            0,
                            wazaName.text.toString(),
                            wazaDescription.text.toString(),
                        )
                        // Here we initialize the database handler class.
                        val dbHandler = DatabaseHandler(this)
                        val addedWazaToDatabase = dbHandler.addWazaToTheDatabase(waza)
                        if (addedWazaToDatabase > 0) {
                            setResult(Activity.RESULT_OK)
                            finish()//finishing activity
                        }
                    }
                }
            }
        }
    }



    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.e("Cancelled", "Cancelled")
        }
    }
}