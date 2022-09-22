package com.example.secondassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var name:String
    private var fruit:Int = 2
    val list = getItemList()
    val adapter = MyAdapter(list)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onAddButtonClickListener()
        buttonsClickListeners()

        val listView = findViewById<RecyclerView>(R.id.myList)

        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(this)
    }

    private fun onAddButtonClickListener(){

        val addButton = findViewById<Button>(R.id.addButton)
        val nameText = findViewById<EditText>(R.id.text)

        addButton.setOnClickListener {
            name = nameText.text.toString()
            println("click")
            adapter.addItem(Item(name,fruit))
        }
    }

    private fun buttonsClickListeners(){
        val bannanaButton = findViewById<ImageButton>(R.id.bannana)
        bannanaButton.setImageResource(R.drawable.bannana)
        bannanaButton.setOnClickListener {
            fruit = 1
        }
        val strawberryButton = findViewById<ImageButton>(R.id.strawberry)
        strawberryButton.setImageResource(R.drawable.strawberry)
        strawberryButton.setOnClickListener {
            fruit = 2
        }
        val peachButton = findViewById<ImageButton>(R.id.peach)
        peachButton.setImageResource(R.drawable.peach)
        peachButton.setOnClickListener {
            fruit = 3
        }
    }

    private fun getItemList(): MutableList<Item>{
        val itemList = mutableListOf<Item>()


        return itemList
    }
}

