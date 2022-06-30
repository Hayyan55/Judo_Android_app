package com.example.ikpmd

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var itemName: String? = null
    private var itemDes: String? = null
    private val namesList = ArrayList<String>()
    private val dbHandler = DatabaseHandler(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wMovesList = dbHandler.getJudoWazaList()
        val listView = findViewById<ListView>(R.id.m_listview)
        val addWaza = findViewById<FloatingActionButton>(R.id.m_addButton)
        addWaza.setOnClickListener{
            val intent = Intent(this@MainActivity, AddNewWaza::class.java)
            startActivity(intent)
        }
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, getWazaNames()
        )

        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { _, _, i, _ ->
            this.itemName = wMovesList[i].waza_name
            this.itemDes = wMovesList[i].description

            val toast = Toast.makeText(this, "${wMovesList[i]}", Toast.LENGTH_LONG)
            toast.show()
            val intent = Intent(this, Specification::class.java)
            intent.putExtra(Constants.name_waza, this.itemName)
            intent.putExtra(Constants.des_waza, this.itemDes)
            startActivityForResult(intent, ADD_WAZA_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // check if the request code is same as what is passed  here it is 'ADD_WAZA_REQUEST_CODE'
        if (requestCode == ADD_WAZA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                getWazaListFromLocalDb()
            }else{
                Log.e("Activity", "Cancelled or Back Pressed")
            }
        }
    }

    private fun getWazaNames(): ArrayList<String> {
        getWazaListFromLocalDb()
        return namesList
    }

    private fun getWazaListFromLocalDb() {
        val getWazaList: ArrayList<Waza> = dbHandler.getJudoWazaList()
        if (getWazaList.size > 0) {
            for (waza in getWazaList) {
                val itemName = waza.waza_name
                namesList.add(itemName)
                Log.e("TitleDB", waza.waza_name)
                Log.e("desDB", waza.description)
            }
        }
    }

    companion object{
        private const val ADD_WAZA_REQUEST_CODE = 1
    }
}