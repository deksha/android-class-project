package com.example.secondassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var name:String
    private lateinit var desc:String
    private var fruit:Int = 2
    val list = getItemList()
    val adapter = MyAdapter(list){
        displayItemDetailsFragment(it)
    }

    lateinit var repository: Repository
    val itemList = mutableListOf<Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_main)
        onAddButtonClickListener()
        buttonsClickListeners()

        val dao = ItemsDataBase.getDatabase(application).getItemsDao()
        repository = Repository(application)

        val listView = findViewById<RecyclerView>(R.id.myList)

        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(this)
        val itemsLiveDate = repository.getAllItemsAsLiveData()
        itemsLiveDate.observe(this){
            adapter.adapterUpdateTheView(it)
        }
    }

    private fun onAddButtonClickListener(){

        val addButton = findViewById<Button>(R.id.addButton)
        val nameText = findViewById<EditText>(R.id.text)
        val itemDesc = findViewById<EditText>(R.id.description)

        addButton.setOnClickListener {
            name = nameText.text.toString()
            desc = itemDesc.text.toString()
            val item = Item(name,fruit,desc)
            thread (start = true){
                repository.addItem(item)
            }
        }
    }

    private fun buttonsClickListeners(){
        val bannanaButton = findViewById<ImageButton>(R.id.bannana)
        bannanaButton.setOnClickListener {
            fruit = 1
        }
        val strawberryButton = findViewById<ImageButton>(R.id.strawberry)
        strawberryButton.setOnClickListener {
            fruit = 2
        }
        val peachButton = findViewById<ImageButton>(R.id.peach)
        peachButton.setOnClickListener {
            fruit = 3
        }
    }

    private fun displayItemDetailsFragment(item: Item){
        val itemFragment = ItemFragment()
        val bundle = bundleOf("theItemTitle" to item.title, "theItemImage" to item.getImage(item.image), "theItemDescription" to item.description)
        itemFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, itemFragment)
            .show(itemFragment)
            .commit()

        val mainScreen = findViewById<ConstraintLayout>(R.id.main_screen)
        mainScreen.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .hide(itemFragment)
                .commit()
        }
    }


    private fun getItemList(): ArrayList<Item>{
        val itemList = ArrayList<Item>()


        return itemList
    }
}

