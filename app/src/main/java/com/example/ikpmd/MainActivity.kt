package com.example.ikpmd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var itemName: String? = null
    private var itemPic: Int? = null
    private val namesList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wMovesList = Constants.getMoves()
        val listView = findViewById<ListView>(R.id.m_listview)
        val addWaza = findViewById<FloatingActionButton>(R.id.m_addButton)

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, getWazaNames()
        )

        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { _, _, i, _ ->
            this.itemName = wMovesList[i].waza_name
            this.itemPic = wMovesList[i].waza_pic

//            val toast = Toast.makeText(this, "${wMovesList[i]}", Toast.LENGTH_LONG)
//            toast.show()
            val intent = Intent(this, Specification::class.java)
            intent.putExtra(Constants.name_waza, this.itemName)
            intent.putExtra(Constants.pic_waza, this.itemPic)
            startActivity(intent)
        }
    }

    private fun getWazaNames(): ArrayList<String> {

        for (waza in Constants.getMoves()) {
            val itemName = waza.waza_name
            namesList.add(itemName)
        }
        return namesList
    }
}